package nwpu.threebull.forum.dao;

import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关于reply的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public interface ReplyRepository {
    /**
     * 通过TopicId统计reply的数目
     *
     * @param TopicId
     * @return
     */
    int countByTopicId(int TopicId);

    /**
     * 新建reply对象
     *
     * @param reply
     */
    void newReply(Reply reply);

    /**
     * 通过TopicId找到reply
     *
     * @param TopicId
     * @return
     */
    List<Reply> findByTopicId(int TopicId);

    /**
     * reply的分页的支持函数
     *
     * @param TopicId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PaginationSupport<Reply> findPageByTopicId(int TopicId, int pageNo, int pageSize);
}
