package nwpu.threebull.forum.service.serviceImpl;

import nwpu.threebull.forum.dao.ReplyRepository;
import nwpu.threebull.forum.dao.TopicRepository;
import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Reply;
import nwpu.threebull.forum.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Reply> findByTopicId(int TopicId) {
        return replyRepository.findByTopicId(TopicId);
    }

    @Override
    public void newReply(Reply reply) {
        replyRepository.newReply(reply);
        topicRepository.updateFollowNumber(reply.getTopicId());
    }

    @Override
    public PaginationSupport<Reply> findPageByTopicId(int TopicId, int pageNo, int pageSize) {
        return replyRepository.findPageByTopicId(TopicId, pageNo, pageSize);
    }
}
