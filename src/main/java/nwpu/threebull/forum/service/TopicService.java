package nwpu.threebull.forum.service;

import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Topic;

import java.util.List;

public interface TopicService {
    int countByUserId(int userId);

    void updateClickNumByTopic(Topic topic);

    List<Topic> findByUserId(int userId);

    Topic findByTopicId(int topicId);

    void updateTitleByTopicId(int topicId, String title, String content);

    PaginationSupport<Topic> findPageByUserId(int userId, int pageNo, int pageSize);

    PaginationSupport<Topic> findPageTopics(int pageNo, int pageSize);
}
