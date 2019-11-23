package nwpu.threebull.forum.service;

import nwpu.threebull.forum.entity.User;

public interface UserService {
    User findUserByUserName(String userName);

    User findUserByUserNameAndPassword(String userName, String password);
}
