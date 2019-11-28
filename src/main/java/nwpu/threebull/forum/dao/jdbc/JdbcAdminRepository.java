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

@Repository
public class JdbcAdminRepository implements AdminRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcAdminRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

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


    @Override
    public List<Admin> findAllAdmins() {
        return jdbc.query(SELECT_ADMIN, new AdminRowMapper());
    }

    @Override
    public void addAdmin(Admin admin) {
        jdbc.update(INSERT_ADMIN, admin.getId(), admin.getUserName(), admin.getPassword());
    }

    @Override
    public void deleteAdminById(int adminId) {
        jdbc.execute(String.format("delete from admin where id = %d", adminId));
    }

    @Override
    public void editAdmin(Admin admin) {
        jdbc.update(UPDATE_ADMIN, admin.getUserName(), admin.getPassword(), admin.getId());
    }

    @Override
    public List<Admin> searchAdminByUsername(String userName) {
        return jdbc.query(SELECT_ADMIN + " where username like ?", new AdminRowMapper(), "%" + userName + "%");
    }

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
