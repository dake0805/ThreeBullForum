package nwpu.threebull.forum.service.serviceImpl;

import nwpu.threebull.forum.dao.AdminRepository;
import nwpu.threebull.forum.entity.Admin;
import nwpu.threebull.forum.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findAdminById(int adminId) {
        return adminRepository.findAdminById(adminId);
    }

    @Override
    public Admin findAdminByAdminNameAndPassword(String userName, String password) {
        return adminRepository.findAdminByAdminNameAndPassword(userName, password);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAllAdmins();
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepository.addAdmin(admin);
    }

    @Override
    public void deleteAdminById(int adminId) {
        adminRepository.deleteAdminById(adminId);
    }

    @Override
    public void editAdmin(Admin admin) {
        adminRepository.editAdmin(admin);
    }

    @Override
    public List<Admin> searchAdminByUsername(String userName) {
        return adminRepository.searchAdminByUsername(userName);
    }


}
