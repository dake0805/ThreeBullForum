package nwpu.threebull.forum.dao;

import nwpu.threebull.forum.entity.Topic;
import org.springframework.stereotype.Repository;

import java.util.*;

import nwpu.threebull.forum.dao.support.PaginationSupport;

public interface TopicRepository {

    int countByUserId(int userId);

    void updateClickNumByTopic(Topic topic);

    List<Topic> findByUserId(int userId);

    Topic findByTopicId(int topicId);

    void updateTitleByTopicId(int topicId, String title, String content);

    void newTopic(Topic topic);

    PaginationSupport<Topic> findPageByUserId(int userId, int pageNo, int pageSize);
}
