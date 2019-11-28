package nwpu.threebull.forum.service;

import nwpu.threebull.forum.dao.support.PaginationSupport;
import nwpu.threebull.forum.entity.User;

import java.util.List;

public interface UserService {
    User findUserByUserName(String userName);

    User findUserByUserNameAndPassword(String userName, String password);

    List<User> findAllUsers();

    void lockUserById(int userId);

    void unLockUserById(int userId);

    void addUser(User user);
}
