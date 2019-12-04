package nwpu.threebull.forum.entity;

import com.sun.istack.internal.NotNull;

import java.sql.Timestamp;

/**
 * reply实体
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public class Reply {

    int id;

    @NotNull
    int topicId;

    @NotNull
    String content;

    @NotNull
    User user;

    @NotNull
    Timestamp time;

    /**
     * @param id
     * @param topicId
     * @param content
     * @param user
     * @param time
     */
    public Reply(int id, int topicId, String content, User user, Timestamp time) {
        this.id = id;
        this.topicId = topicId;
        this.content = content;
        this.user = user;
        this.time = time;
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
    public int getTopicId() {
        return topicId;
    }

    /**
     * @param topicId
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
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
    public Timestamp getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
