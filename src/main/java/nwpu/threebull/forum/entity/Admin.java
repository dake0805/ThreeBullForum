package nwpu.threebull.forum.entity;

import com.sun.istack.internal.NotNull;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getPassword() + " " + this.getUserName();
    }
}
