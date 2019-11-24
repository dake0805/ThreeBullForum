package nwpu.threebull.forum.dao;

import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReplyRepository {
    int countByTopicId(int TopicId);

    void newReply(Reply reply);

    List<Reply> findByTopicId(int TopicId);

    PaginationSupport<Reply> findPageByTopicId(int TopicId, int pageNo, int pageSize);
}
