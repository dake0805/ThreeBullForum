package nwpu.threebull.forum.service.serviceImpl;

import nwpu.threebull.forum.dao.ReplyRepository;
import nwpu.threebull.forum.dao.TopicRepository;
import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Reply;
import nwpu.threebull.forum.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 *实现ReplyService的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TopicRepository topicRepository;

    /**
     * 通过TopicId找到reply
     * @param TopicId
     * @return
     */
    @Override
    public List<Reply> findByTopicId(int TopicId) {
        return replyRepository.findByTopicId(TopicId);
    }

    /**
     * 新建reply对象
     * @param reply
     */
    @Override
    public void newReply(Reply reply) {
        replyRepository.newReply(reply);
        topicRepository.updateFollowNumber(reply.getTopicId());
    }

    /**
     * reply的分页的支持函数
     * @param TopicId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PaginationSupport<Reply> findPageByTopicId(int TopicId, int pageNo, int pageSize) {
        return replyRepository.findPageByTopicId(TopicId, pageNo, pageSize);
    }
}
