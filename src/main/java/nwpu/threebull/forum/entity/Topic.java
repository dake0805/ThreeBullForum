package nwpu.threebull.forum.entity;



import javax.validation.constraints.NotNull;
import java.sql.*;

import java.util.Date;

/**
 * topic实体
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public class Topic {

    private int id;

    @NotNull
    private String title;

    private String content;

    @NotNull
    private User user;


    private boolean topicStatus;

    private Timestamp topTime;

    @NotNull
    private Timestamp postTime;

    @NotNull
    private int clickNum;

    @NotNull
    private int followNum;

    /**
     * @param id
     * @param title
     * @param content
     * @param user
     * @param topicStatus
     * @param topTime
     * @param postTime
     * @param clickNum
     * @param followNum
     */
    public Topic(int id, String title, String content, User user, boolean topicStatus, Timestamp topTime, Timestamp postTime, int clickNum, int followNum) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.topicStatus = topicStatus;
        this.topTime = topTime;
        this.postTime = postTime;
        this.clickNum = clickNum;
        this.followNum = followNum;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return
     */
    public boolean isTopicStatus() {
        return topicStatus;
    }

    /**
     * @param topicStatus
     */
    public void setTopicStatus(boolean topicStatus) {
        this.topicStatus = topicStatus;
    }

    /**
     * @return
     */
    public Timestamp getTopTime() {
        return topTime;
    }

    /**
     * @param topTime
     */
    public void setTopTime(Timestamp topTime) {
        this.topTime = topTime;
    }

    /**
     * @return
     */
    public Timestamp getPostTime() {
        return postTime;
    }

    /**
     * @param postTime
     */
    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    /**
     * @return
     */
    public int getClickNum() {
        return clickNum;
    }

    /**
     * @param clickNum
     */
    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    /**
     * @return
     */
    public int getFollowNum() {
        return followNum;
    }

    /**
     * @param followNum
     */
    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }
}
