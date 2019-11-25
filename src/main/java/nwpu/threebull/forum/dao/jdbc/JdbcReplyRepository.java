package nwpu.threebull.forum.dao.jdbc;

import nwpu.threebull.forum.dao.ReplyRepository;
import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Reply;
import nwpu.threebull.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcReplyRepository implements ReplyRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcReplyRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int countByTopicId(int TopicId) {
        int count = jdbc.queryForObject("select count(*) from reply where topic_id = ?", new Object[]{TopicId}, Integer.class);
        return count;
    }

    @Override
    public List<Reply> findByTopicId(int TopicId) {
        return jdbc.query(SELECT_TOPIC_BY_TOPICID, new JdbcReplyRepository.ReplyRowMapper(), TopicId);
    }

    @Override
    public PaginationSupport<Reply> findPageByTopicId(int TopicId, int pageNo, int pageSize) {
        int totalCount = countByTopicId(TopicId);
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<Reply>(new ArrayList<Reply>(0), 0);

        List<Reply> items = jdbc.query(SELECT_PAGE_REPLY_BY_TOPICID, new JdbcReplyRepository.ReplyRowMapper(), TopicId, pageSize, startIndex);
        PaginationSupport<Reply> ps = new PaginationSupport<Reply>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public void newReply(Reply reply){

        jdbc.update(INSERT_REPLY,reply.getId(),reply.getTopicId(),reply.getContent(),reply.getUser().getId(),reply.getTime());
    }

    private static final class ReplyRowMapper implements RowMapper<Reply> {
        public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            int topicId = rs.getInt("topic_id");
            String content = rs.getString("content");
            Timestamp time = rs.getTimestamp("time");

            int userId = rs.getInt("userId");
            String userName = rs.getString("username");
            String password = rs.getString("password");
            boolean isLocked = rs.getBoolean("lock_status");

            User user = new User(userId, userName, password, isLocked);
            return new Reply(id, topicId, content, user, time);
        }
    }

    private static final String SELECT_REPLYS = "select r.id, u.id as userId, u.username, u.password, u.lock_status, r.topic_id, r.content, r.time from Reply r, User u where r.user_id = u.id";
    private static final String SELECT_TOPIC_BY_TOPICID = SELECT_REPLYS + " and r.topic_id=?";
    private static final String SELECT_PAGE_REPLY_BY_TOPICID = SELECT_TOPIC_BY_TOPICID
            + " order by r.time desc limit ? offset  ?";
    private static final String INSERT_REPLY ="insert into reply(id,topic_id,content,user_id,time) values( ?, ?, ?, ?, ?)";
}
