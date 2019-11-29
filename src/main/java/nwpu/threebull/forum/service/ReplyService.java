package nwpu.threebull.forum.service;

import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Reply;

import java.util.List;

/**
 * ReplyService的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public interface ReplyService {
    /**
     * 通过TopicId找到reply
     * @param TopicId
     * @return
     */
    List<Reply> findByTopicId(int TopicId);

    /**
     * 新建reply对象
     * @param reply
     */
    void newReply(Reply reply);

    /**
     * reply的分页的支持函数
     * @param TopicId
     * @param pageNo
     * @param pageSize
     * @return
     */

    PaginationSupport<Reply> findPageByTopicId(int TopicId, int pageNo, int pageSize);
}
