package nwpu.threebull.forum.entity;

import com.sun.istack.internal.NotNull;

import java.sql.Timestamp;

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

    public Reply(int id, int topicId, String content, User user, Timestamp time) {
        this.id = id;
        this.topicId = topicId;
        this.content = content;
        this.user = user;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
