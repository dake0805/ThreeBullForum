package nwpu.threebull.forum.dao;

import org.springframework.stereotype.Repository;
import nwpu.threebull.forum.entity.User;

import java.util.List;

/**
 * 关于user的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
@Repository
public interface UserRepository {
    /**
     * 根据userName找到user对象
     *
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 根据userName和password找到user对象
     *
     * @param userName
     * @param password
     * @return
     */
    User findUserByUserNameAndPassword(String userName, String password);

    /**
     * 返回所有的user
     *
     * @return
     */
    List<User> findAllUsers();

    /**
     * 根据userId将user设置为上锁状态
     *
     * @param userId
     */
    void lockUserById(int userId);

    /**
     * 根据userId将user设置为未上锁状态
     *
     * @param userId
     */
    void unLockUserById(int userId);

    /**
     * 添加user对象到数据库中
     *
     * @param user
     */
    void addUser(User user);

    /**
     * 更新user对象到数据库中
     *
     * @param user
     */
    void editUser(User user);
}
