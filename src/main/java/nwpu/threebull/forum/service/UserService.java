package nwpu.threebull.forum.service;

import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.User;

import java.util.List;
/**
 * UserService的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public interface UserService {
    /**
     * 根据userName找到user对象
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

    /**
     * 根据userName和password找到user对象
      * @param userName
     * @param password
     * @return
     */
    User findUserByUserNameAndPassword(String userName, String password);

    /**
     * 返回所有的user
     * @return
     */
    List<User> findAllUsers();

    /**
     * 根据userId将user设置为上锁状态
     * @param userId
     */
    void lockUserById(int userId);

    /**
     * 根据userId将user设置为未上锁状态
     * @param userId
     */
    void unLockUserById(int userId);

    /**
     * 添加user对象到数据库中
     * @param user
     */
    void addUser(User user);

    void editUser(User user);
}
