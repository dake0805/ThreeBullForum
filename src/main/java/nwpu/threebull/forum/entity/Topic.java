package nwpu.threebull.forum.entity;

import com.sun.istack.internal.NotNull;

import java.sql.*;

import java.util.Date;

public class Topic {
    private int id;

    @NotNull
    private String title;

    private String content;

    @NotNull
    private User user;

    /**
     *
     */
    private boolean topicStatus;

    private Timestamp topTime;

    @NotNull
    private Timestamp postTime;

    @NotNull
    private int clickNum;

    @NotNull
    private int followNum;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(boolean topicStatus) {
        this.topicStatus = topicStatus;
    }

    public Timestamp getTopTime() {
        return topTime;
    }

    public void setTopTime(Timestamp topTime) {
        this.topTime = topTime;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }
}
