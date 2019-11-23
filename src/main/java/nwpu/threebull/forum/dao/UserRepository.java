package nwpu.threebull.forum.dao;

import org.springframework.stereotype.Repository;
import nwpu.threebull.forum.entity.User;

@Repository
public interface UserRepository {
    User findUserByUserName(String userName);

    User findUserByUserNameAndPassword(String userName, String password);
}
