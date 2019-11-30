package nwpu.threebull.forum.entity;

import com.sun.istack.internal.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * user实体
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public class User {
    private int id;


    @NotEmpty
    @NotBlank
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

    /**
     *
     * @param id
     * @param userName
     * @param password
     */
    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isLocked = false;
    }

    /**
     *
     * @param id
     * @param userName
     * @param password
     * @param isLocked
     */
    public User(int id, String userName, String password, boolean isLocked) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isLocked = isLocked;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean equals(User user) {
        return user.getId() == this.id;
    }
}
