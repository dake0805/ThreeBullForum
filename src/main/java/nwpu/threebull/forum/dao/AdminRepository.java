package nwpu.threebull.forum.dao;

import nwpu.threebull.forum.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AdminRepository {

    Admin findAdminById(int adminId);

    Admin findAdminByAdminNameAndPassword(String userName, String password);

    List<Admin> findAllAdmins();

    void addAdmin(Admin admin);

    void deleteAdminById(int adminId);

    void editAdmin(Admin admin);
}
