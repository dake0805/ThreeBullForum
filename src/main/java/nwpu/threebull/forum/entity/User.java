package nwpu.threebull.forum.entity;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {
    private int id;


    @NotEmpty
    @Size(min = 2, max = 30)
    private
    String userName;

    @NotEmpty
    @Size(min = 2, max = 30)
    private
    String password;

    @NotNull
    private
    boolean isLocked;

    public User() {

    }

    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isLocked = false;
    }

    public User(int id, String userName, String password, boolean isLocked) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isLocked = isLocked;
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

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean equals(User user) {
        return user.getId() == this.id;
    }
}
