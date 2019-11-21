package nwpu.threebull.forum.entity;

import com.sun.istack.internal.NotNull;

import java.util.Date;

public class Topic {
    private int id;

    @NotNull
    private String title;

    private String content;

    @NotNull
    private int userId;

    /**
     *
     */
    private boolean topicStatus;

    @NotNull
    private int clickNum;

}
