package nwpu.threebull.forum.dao.jdbc;

import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Topic;
import nwpu.threebull.forum.dao.TopicRepository;
import nwpu.threebull.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Repository
public class JdbcTopicRepository implements TopicRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTopicRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int countByUserId(int userId) {
        int count = jdbc.queryForObject("select count(*) from topic where user_id = ?", Integer.class, new Object[]{userId});
        return count;
    }

    @Override
    public int countAllTopics() {
        int count = jdbc.queryForObject("select count(*) from topic", Integer.class);
        return count;
    }

    @Override
    public int countSearchTopics(String info, String type) {
        //参数原型为public <T> T queryForObject(String sql, Class<T> requiredType, Object... args)
        int count = 0;
        switch (type) {
            case "TITLE":
                count = jdbc.queryForObject("select count(*) from topic where name like  ?", Integer.class, new Object[]{"%" + info + "%"});
                break;
            case "CONTENT":
                count = jdbc.queryForObject("select count(*) from topic where content like  ?", Integer.class, new Object[]{"%" + info + "%"});
                break;
            case "TITLEORCONTENT":
                count = jdbc.queryForObject("select count(*) from topic where content like  ? or name like ?", Integer.class, new Object[]{"%" + info + "%", "%" + info + "%"});
                break;
        }
        return count;
    }

    @Override
    public void updateClickNumByTopic(Topic topic) {
        int clickNum = topic.getClickNum();
        clickNum++;
        jdbc.update("update topic set click_number = ? where id = ?", clickNum, topic.getId());
    }

    @Override
    public List<Topic> findByUserId(int userId) {
        return jdbc.query(SELECT_TOPIC_BY_USERID, new TopicRowMapper(), userId);
    }


    public Topic findByTopicId(int topicId) {
        return jdbc.queryForObject(SELECT_TOPIC_BY_TOPICID, new TopicRowMapper(), topicId);
    }

    public void updateTitleByTopicId(int topicId, String title, String content) {
        jdbc.update("update topic set name = ?, content = ? where id = ?", title, content, topicId);
    }

    public void newTopic(Topic topic) {
        jdbc.update(INSERT_TOPIC, topic.getId(), topic.getTitle(), topic.getContent(), topic.getUser().getId(), topic.isTopicStatus(), topic.getTopTime(),
                topic.getPostTime(), topic.getFollowNum(), topic.getClickNum());
    }

    @Override
    public void deleteTopic(int topicId) {
        jdbc.execute(String.format("delete from topic where id = %d", topicId));
    }

    @Override
    public void topTopic(int topicId) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        jdbc.update("update topic set top_status = ?, top_time = ? where id = ?", 1, timestamp, topicId);
    }

    @Override
    public void unTopTopic(int topicId) {
        jdbc.update("update topic set top_status = ?,top_time =null  where id = ?", 0, topicId);
    }

    @Override
    public PaginationSupport<Topic> findPageByUserId(int userId, int pageNo, int pageSize) {
        int totalCount = countByUserId(userId);
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1)
            return new PaginationSupport<Topic>(new ArrayList<Topic>(0), 0);

        List<Topic> items = jdbc.query(SELECT_PAGE_TOPIC_BY_USERID, new TopicRowMapper(), userId, pageSize, startIndex);
        PaginationSupport<Topic> ps = new PaginationSupport<Topic>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public PaginationSupport<Topic> findPageTopics(int pageNo, int pageSize) {
        int totalCount = countAllTopics();
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1) {
            return new PaginationSupport<Topic>(new ArrayList<Topic>(0), 0);
        }
        List<Topic> items = jdbc.query(SELECT_PAGE_TOPIC, new TopicRowMapper(), pageSize, startIndex);
        PaginationSupport<Topic> ps = new PaginationSupport<Topic>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public PaginationSupport<Topic> findPageTopicsByTitleOrContent(String info, String type, int pageNo, int pageSize) {
        int totalCount = countSearchTopics(info, type);
        int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
        if (totalCount < 1) {
            return new PaginationSupport<Topic>(new ArrayList<Topic>(0), 0);
        }
        String sql = null;
        List<Topic> items = null;
        switch (type) {
            case "TITLE":
                sql = SELECT_PAGE_TOPIC_BY_TITLE;
                items = jdbc.query(sql, new TopicRowMapper(), "%" + info + "%", pageSize, startIndex);
                break;
            case "CONTENT":
                sql = SELECT_PAGE_TOPIC_BY_CONTENT;
                items = jdbc.query(sql, new TopicRowMapper(), "%" + info + "%", pageSize, startIndex);
                break;
            case "TITLEORCONTENT":
                sql = SELECT_PAGE_TOPIC_BY_INFO;
                items = jdbc.query(sql, new TopicRowMapper(), "%" + info + "%", "%" + info + "%", pageSize, startIndex);
                break;
            default:
        }
        PaginationSupport<Topic> ps = new PaginationSupport<Topic>(items, totalCount, pageSize, startIndex);
        return ps;
    }

    @Override
    public void updateFollowNumber(int topicId) {
        jdbc.update(UPDATE_FOLLOW_NUMBER, topicId);
    }

    private static final class TopicRowMapper implements RowMapper<Topic> {
        public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String title = rs.getString("name");
            String content = rs.getString("content");
            boolean topicStatus = rs.getBoolean("top_status");
            Timestamp topTime = rs.getTimestamp("top_time");
            Timestamp postTime = rs.getTimestamp("post_time");
            int clickNum = rs.getInt("click_number");
            int followNum = rs.getInt("follow_number");

            int userId = rs.getInt("userId");
            String userName = rs.getString("username");
            String password = rs.getString("password");
            boolean isLocked = rs.getBoolean("lock_status");

            User user = new User(userId, userName, password, isLocked);
            return new Topic(id, title, content, user, topicStatus, topTime,
                    postTime, clickNum, followNum);
        }
    }

    private static final String UPDATE_FOLLOW_NUMBER = "update topic set follow_number=follow_number+1 where id=?";
    private static final String SELECT_TOPIC = "select t.id, u.id as userId, u.username, u.password, u.lock_status, t.name, t.content, t.top_status, t.top_time, t.post_time, t.follow_number, t.click_number from topic t, user u where t.user_id = u.id";
    private static final String SELECT_TOPIC_BY_USERID = SELECT_TOPIC + " and u.id=?";
    private static final String SELECT_TOPIC_BY_TOPICID = SELECT_TOPIC + " and t.id=?";

    private static final String SELECT_TOPIC_BY_TITLE = SELECT_TOPIC + " and t.name like ?";
    private static final String SELECT_TOPIC_BY_CONTENT = SELECT_TOPIC + " and t.content like ?";
    private static final String SELECT_TOPIC_BY_INFO = SELECT_TOPIC + " and( t.name like ? or t.content like ?)";

    private static final String SELECT_TOPIC_ORDER_BY = " order by t.top_time desc ,t.post_time desc limit ? offset  ?";

    private static final String SELECT_PAGE_TOPIC_BY_USERID = SELECT_TOPIC_BY_USERID + SELECT_TOPIC_ORDER_BY;
    private static final String SELECT_PAGE_TOPIC_BY_TITLE = SELECT_TOPIC_BY_TITLE + SELECT_TOPIC_ORDER_BY;
    private static final String SELECT_PAGE_TOPIC_BY_CONTENT = SELECT_TOPIC_BY_CONTENT + SELECT_TOPIC_ORDER_BY;
    private static final String SELECT_PAGE_TOPIC_BY_INFO = SELECT_TOPIC_BY_INFO + SELECT_TOPIC_ORDER_BY;
    private static final String SELECT_PAGE_TOPIC = SELECT_TOPIC + SELECT_TOPIC_ORDER_BY;

    private static final String INSERT_TOPIC = "insert into topic ( id,name, content, user_id,top_status,top_time," +
            "post_time,follow_number,click_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

}
