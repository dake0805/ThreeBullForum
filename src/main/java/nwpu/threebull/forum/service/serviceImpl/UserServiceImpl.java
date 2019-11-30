package nwpu.threebull.forum.service.serviceImpl;


import nwpu.threebull.forum.dao.UserRepository;
import nwpu.threebull.forum.entity.User;
import nwpu.threebull.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 *实现UserService的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     *
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * 根据userName找到user对象
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    /**
     * 根据userName和password找到user对象
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        return userRepository.findUserByUserNameAndPassword(userName, password);
    }

    /**
     * 返回所有的user
     * @return
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    /**
     * 根据userId将user设置为上锁状态
     * @param userId
     */
    @Override
    public void lockUserById(int userId) {
        userRepository.lockUserById(userId);
    }

    /**
     * 根据userId将user设置为未上锁状态
     * @param userId
     */
    @Override
    public void unLockUserById(int userId) {
        userRepository.unLockUserById(userId);
    }

    /**
     * 添加user对象到数据库中
     * @param user
     */
    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public void editUser(User user) {
        userRepository.editUser(user);
    }
}
