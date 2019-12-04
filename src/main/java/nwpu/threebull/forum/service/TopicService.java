package nwpu.threebull.forum.service;

import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Topic;

import java.util.List;

/**
 * TopicService的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public interface TopicService {
    /**
     * 通过userId查找一个用户发表了多少主题
     *
     * @param userId
     * @return
     */
    int countByUserId(int userId);

    /**
     * 根据topic对象更新点击数
     *
     * @param topic
     */
    void updateClickNumByTopic(Topic topic);

    /**
     * 根据userId找到该用户发的所有topic
     *
     * @param userId
     * @return
     */
    List<Topic> findByUserId(int userId);

    /**
     * 根据topicId找到该topic
     *
     * @param topicId
     * @return
     */
    Topic findByTopicId(int topicId);

    /**
     * 根据topicId更新topic的主题和内容
     *
     * @param topicId
     * @param title
     * @param content
     */
    void updateTitleByTopicId(int topicId, String title, String content);

    /**
     * 根据topic对象新建一个topic并插入到数据库中
     *
     * @param topic
     */
    void newTopic(Topic topic);

    /**
     * 根据topicId删除topic
     *
     * @param topicId
     */
    void deleteTopic(int topicId);

    /**
     * 根据topicId将相应的topic置顶状态改为true
     *
     * @param topicId
     */
    void topTopic(int topicId);

    /**
     * 根据topicId将topic改为非置顶状态
     *
     * @param topicId
     */
    void unTopTopic(int topicId);

    /**
     * 关于topic的分页处理
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    PaginationSupport<Topic> findPageByUserId(int userId, int pageNo, int pageSize);

    /**
     * 关于topic的分页处理
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PaginationSupport<Topic> findPageTopics(int pageNo, int pageSize);

    /**
     * 关于topic的分页处理
     *
     * @param info
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    PaginationSupport<Topic> findPageByTopicTitleOrContent(String info, String type, int pageNo, int pageSize);

    /**
     * 关于mytopic的分页处理
     *
     * @param info
     * @param userID
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    PaginationSupport<Topic> findPageByMyTopicTitleOrContent(String info, int userID, String type, int pageNo, int pageSize);
}
