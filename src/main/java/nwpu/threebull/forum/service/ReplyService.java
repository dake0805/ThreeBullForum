package nwpu.threebull.forum.service;

import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> findByTopicId(int TopicId);

    void newReply(Reply reply);


    PaginationSupport<Reply> findPageByTopicId(int TopicId, int pageNo, int pageSize);
}
