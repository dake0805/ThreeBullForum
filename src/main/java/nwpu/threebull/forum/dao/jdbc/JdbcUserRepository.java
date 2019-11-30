package nwpu.threebull.forum.dao.jdbc;

import nwpu.threebull.forum.dao.UserRepository;
import nwpu.threebull.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 实现UserRepository接口中声明方法
 *
 * @author ThreeBullForumTeam
 * @version 1.0
 */
@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbc;

    /**
     * @param jdbc
     */
    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     *
     * 根据userName找到user对象
     *
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        User user = null;
        try {
            user = jdbc.queryForObject(SELECT_USER + " where username=?", new UserRowMapper(),
                    userName);
        } catch (Exception e) {
        }
        return user;
    }

    /**
     *
     * 根据userName和password找到user对象
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        User user = null;
        try {
            user = jdbc.queryForObject(SELECT_USER + " where username=? and password=?", new UserRowMapper(),
                    userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     *
     * 返回所有的user
     *
     * @return
     */
    @Override
    public List<User> findAllUsers() {
        return jdbc.query(SELECT_USER, new UserRowMapper());
    }

    /**
     *
     * 根据userId将user设置为上锁状态
     *
     * @param userId
     */
    @Override
    public void lockUserById(int userId) {
        // "1" means locked.
        jdbc.update("update user set lock_status = ? where id = ?", 1, userId);
    }

    /**
     *
     * 根据userId将user设置为未上锁状态
     *
     * @param userId
     */
    @Override
    public void unLockUserById(int userId) {
        jdbc.update("update user set lock_status = ? where id = ?", 0, userId);
    }

    /**
     *
     * 添加user对象到数据库中
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        jdbc.update(INSERT_USER, user.getId(), user.getUserName(), user.getPassword());

    }

    @Override
    public void editUser(User user) {
        jdbc.update(UPDATE_USER, user.getUserName(), user.getPassword(), user.getId());
    }


    /**
     *
     */
    private static class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getBoolean("lock_status"));
        }
    }


    private static final String SELECT_USER = "select id, username, password, lock_status from user";
    private static final String INSERT_USER = "insert into user (id, username, password) values (?, ?, ?)";
    private static final String UPDATE_USER = "update user set username = ?, password = ? where id = ?";
}
