package nwpu.threebull.forum.service;

import nwpu.threebull.forum.entity.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> searchAdminByUsername(String userName);

    Admin findAdminById(int adminId);

    Admin findAdminByAdminNameAndPassword(String name, String password);

    List<Admin> findAllAdmins();

    void addAdmin(Admin admin);

    void deleteAdminById(int adminId);

    void editAdmin(Admin admin);
}
