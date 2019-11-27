package nwpu.threebull.forum.dao;

import org.springframework.stereotype.Repository;
import nwpu.threebull.forum.entity.User;

import java.util.List;

@Repository
public interface UserRepository {
    User findUserByUserName(String userName);

    User findUserByUserNameAndPassword(String userName, String password);

    List<User> findAllUsers();

    void lockUserById(int userId);

    void unLockUserById(int userId);

    void addUser(User user);
}
