package nwpu.threebull.forum.service.serviceImpl;

import nwpu.threebull.forum.dao.TopicRepository;
import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.Topic;
import nwpu.threebull.forum.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 *
 *实现TopicService的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    /**
     * 通过userId查找一个用户发表了多少主题
     * @param userId
     * @return
     */
    @Override
    public int countByUserId(int userId) {
        return topicRepository.countByUserId(userId);
    }

    /**
     * 根据topic对象更新点击数
     * @param topic
     */
    @Override
    public void updateClickNumByTopic(Topic topic) {
        topicRepository.updateClickNumByTopic(topic);
    }

    /**
     * 根据userId找到该用户发的所有topic
     * @param userId
     * @return
     */
    @Override
    public List<Topic> findByUserId(int userId) {
        return topicRepository.findByUserId(userId);
    }

    /**
     * 根据topicId找到该topic
     * @param topicId
     * @return
     */
    @Override
    public Topic findByTopicId(int topicId) {
        return topicRepository.findByTopicId(topicId);
    }

    /**
     * 根据topicId更新topic的主题和内容
     * @param topicId
     * @param title
     * @param content
     */
    @Override
    public void updateTitleByTopicId(int topicId, String title, String content) {
        topicRepository.updateTitleByTopicId(topicId, title, content);
    }

    /**
     * 根据topic对象新建一个topic并插入到数据库中
     * @param topic
     */
    @Override
    public void newTopic(Topic topic){
        topicRepository.newTopic(topic);
    }

    /**
     * 根据topicId删除topic
     * @param topicId
     */
    @Override
    public void deleteTopic(int topicId) {
        topicRepository.deleteTopic(topicId);
    }

    /**
     *根据topicId将相应的topic置顶状态改为true
     * @param topicId
     */
    @Override
    public void topTopic(int topicId) {
        topicRepository.topTopic(topicId);
    }

    /**
     * 根据topicId将topic改为非置顶状态
     * @param topicId
     */
    @Override
    public void unTopTopic(int topicId) {
        topicRepository.unTopTopic(topicId);
    }

    /**
     * 关于topic的分页处理
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PaginationSupport<Topic> findPageByUserId(int userId, int pageNo, int pageSize) {
        return topicRepository.findPageByUserId(userId, pageNo, pageSize);
    }

    /**
     * 关于topic的分页处理
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PaginationSupport<Topic> findPageTopics(int pageNo, int pageSize) {
        return topicRepository.findPageTopics(pageNo, pageSize);
    }

    /**
     * 关于topic的分页处理
     * @param info
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PaginationSupport<Topic> findPageByTopicTitleOrContent(String info, String type, int pageNo, int pageSize) {
        return topicRepository.findPageTopicsByTitleOrContent(info, type, pageNo, pageSize);
    }
}
