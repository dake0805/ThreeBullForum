package nwpu.threebull.forum.dao.jdbc;

import nwpu.threebull.forum.dao.AdminRepository;
import nwpu.threebull.forum.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcAdminRepository implements AdminRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcAdminRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Admin findAdminById(int id) {
        Admin admin = null;
        admin = jdbc.queryForObject(SELECT_ADMIN + " where id=?", new AdminRowMapper(),
                id);
        return admin;
    }

    private static class AdminRowMapper implements RowMapper<Admin> {
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Admin(rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"));
        }
    }

    private static final String SELECT_ADMIN = "select id, username, password from admin";

}
