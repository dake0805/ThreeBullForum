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

@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

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

    @Override
    public List<User> findAllUsers() {
        return jdbc.query(SELECT_USER, new UserRowMapper());
    }

    @Override
    public void lockUserById(int userId) {
        // "1" means locked.
        jdbc.update("update user set lock_status = ? where id = ?", 1, userId);
    }


    private static class UserRowMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getBoolean("lock_status"));
        }
    }


    private static final String SELECT_USER = "select id, username, password, lock_status from user";
}
