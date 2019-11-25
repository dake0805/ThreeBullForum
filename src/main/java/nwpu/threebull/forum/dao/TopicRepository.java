package nwpu.threebull.forum.dao;

import nwpu.threebull.forum.entity.Topic;
import org.springframework.stereotype.Repository;

import java.util.*;

import nwpu.threebull.forum.dao.support.PaginationSupport;

public interface TopicRepository {

    int countByUserId(int userId);

    int countAllTopics();

    int countSearchTopics(String info, String type);

    void updateClickNumByTopic(Topic topic);

    List<Topic> findByUserId(int userId);

    Topic findByTopicId(int topicId);

    void updateTitleByTopicId(int topicId, String title, String content);

    void newTopic(Topic topic);

    void deleteTopic(int topicId);

    void topTopic(int topicId);

    PaginationSupport<Topic> findPageByUserId(int userId, int pageNo, int pageSize);

    PaginationSupport<Topic> findPageTopics(int pageNo, int pageSize);

    PaginationSupport<Topic> findPageTopicsByTitleOrContent(String info, String type, int pageNo, int pageSize);
}
