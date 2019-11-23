package nwpu.threebull.forum.service.serviceImpl;

import nwpu.threebull.forum.dao.TopicRepository;
import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Topic;
import nwpu.threebull.forum.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public int countByUserId(int userId) {
        return topicRepository.countByUserId(userId);
    }

    @Override
    public void updateClickNumByTopic(Topic topic) {
        topicRepository.updateClickNumByTopic(topic);
    }

    @Override
    public List<Topic> findByUserId(int userId) {
        return topicRepository.findByUserId(userId);
    }

    @Override
    public Topic findByTopicId(int topicId) {
        return topicRepository.findByTopicId(topicId);
    }

    @Override
    public void updateTitleByTopicId(int topicId, String title, String content) {
        topicRepository.updateTitleByTopicId(topicId, title, content);
    }

    @Override
    public PaginationSupport<Topic> findPageByUserId(int userId, int pageNo, int pageSize) {
        return topicRepository.findPageByUserId(userId, pageNo, pageSize);
    }

    @Override
    public PaginationSupport<Topic> findPageTopics(int pageNo, int pageSize) {
        return topicRepository.findPageTopics(pageNo, pageSize);
    }

    @Override
    public PaginationSupport<Topic> findPageByTopicTitleOrContent(String info, int pageNo, int pageSize) {
        return topicRepository.findPageTopicsByTitleOrContent(info, pageNo, pageSize);
    }
}
