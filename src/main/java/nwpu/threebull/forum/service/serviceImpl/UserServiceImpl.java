package nwpu.threebull.forum.service.serviceImpl;


import nwpu.threebull.forum.dao.UserRepository;
import nwpu.threebull.forum.entity.User;
import nwpu.threebull.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        return userRepository.findUserByUserNameAndPassword(userName, password);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public void lockUserById(int userId) {
        userRepository.lockUserById(userId);
    }
}
