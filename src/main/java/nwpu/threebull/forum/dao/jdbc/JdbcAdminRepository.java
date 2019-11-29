package nwpu.threebull.forum.dao.jdbc;

import nwpu.threebull.forum.dao.AdminRepository;
import nwpu.threebull.forum.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 实现AdminRepository中定义的方法
 *
 * @author ThreeBullForumTeam
 * @version 1.0
 */
@Repository
public class JdbcAdminRepository implements AdminRepository {

    private JdbcTemplate jdbc;

    /**
     *
     * @param jdbc
     */
    @Autowired
    public JdbcAdminRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * 通过adminId找到admin对象
     * @param adminId
     * @return admin
     */
    @Override
    public Admin findAdminById(int adminId) {
        Admin admin = null;
        try {
            admin = jdbc.queryForObject(SELECT_ADMIN + " where id=? ", new AdminRowMapper(),
                    adminId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    /**
     * 通过userName，password找到admin对象
     * @param userName
     * @param password
     * @return admin
     */
    @Override
    public Admin findAdminByAdminNameAndPassword(String userName, String password) {
        Admin admin = null;
        try {
            admin = jdbc.queryForObject(SELECT_ADMIN + " where username=? and password=?", new AdminRowMapper(),
                    userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    /**
     * 通过userName找到admin对象
     * @param userName
     * @return admin
     */
    @Override
    public Admin findAdminByAdminName(String userName) {
        Admin admin = null;
        try {
            admin = jdbc.queryForObject(SELECT_ADMIN + " where username=? ", new AdminRowMapper(),
                    userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    /**
     * 找到所有的管理员
     * @return  List<Admin>
     */
    @Override
    public List<Admin> findAllAdmins() {
        return jdbc.query(SELECT_ADMIN, new AdminRowMapper());
    }

    /**
     * 通过admin对象将admin添加到数据库
     * @param
     */
    @Override
    public void addAdmin(Admin admin) {
        jdbc.update(INSERT_ADMIN, admin.getId(), admin.getUserName(), admin.getPassword());
    }

    /**
     * 通过adminId删除admin对象
     * @param
     */
    @Override
    public void deleteAdminById(int adminId) {
        jdbc.execute(String.format("delete from admin where id = %d", adminId));
    }

    /**
     * 通过新的admin对象来更新admin对象
     * @param admin
     */
    @Override
    public void editAdmin(Admin admin) {
        jdbc.update(UPDATE_ADMIN, admin.getUserName(), admin.getPassword(), admin.getId());
    }

    /**
     * 通过userName查找所有同名的admin
     * @param userName
     * @return List<Admin>
     */
    @Override
    public List<Admin> searchAdminByUsername(String userName) {
        return jdbc.query(SELECT_ADMIN + " where username like ?", new AdminRowMapper(), "%" + userName + "%");
    }

    /**
     *
     */
    private static class AdminRowMapper implements RowMapper<Admin> {
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Admin(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"));
        }
    }

    private static final String SELECT_ADMIN = "select id, username, password from admin";

    private static final String INSERT_ADMIN = "insert into admin (id, username, password) values (?, ?, ?)";

    private static final String UPDATE_ADMIN = "update admin set username = ?, password = ? where id = ?";
}
