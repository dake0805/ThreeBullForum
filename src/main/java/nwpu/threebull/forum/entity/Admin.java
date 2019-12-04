package nwpu.threebull.forum.entity;

import com.sun.istack.internal.NotNull;

/**
 * admin实体
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public class Admin {

    private int id;

    @NotNull
    private String userName;

    @NotNull
    private String password;

    /**
     * Constructor
     *
     * @param id
     * @param userName
     * @param password
     */
    public Admin(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
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
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.getId() + " " + this.getPassword() + " " + this.getUserName();
    }
}
