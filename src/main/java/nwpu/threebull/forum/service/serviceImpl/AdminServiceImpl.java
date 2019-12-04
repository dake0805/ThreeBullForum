package nwpu.threebull.forum.service.serviceImpl;

import nwpu.threebull.forum.dao.AdminRepository;
import nwpu.threebull.forum.entity.Admin;
import nwpu.threebull.forum.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现AdminService的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * 通过adminId找到admin对象
     *
     * @param adminId
     * @return
     */
    @Override
    public Admin findAdminById(int adminId) {
        return adminRepository.findAdminById(adminId);
    }

    /**
     * 通过userName，password找到admin对象
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Admin findAdminByAdminNameAndPassword(String userName, String password) {
        return adminRepository.findAdminByAdminNameAndPassword(userName, password);
    }

    /**
     * 通过userName查找所有同名的admin
     *
     * @param userName
     * @return
     */
    @Override
    public Admin findAdminByAdminName(String userName) {
        return adminRepository.findAdminByAdminName(userName);
    }

    /**
     * 找到所有的管理员
     *
     * @return
     */
    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAllAdmins();
    }

    /**
     * 通过admin对象将admin添加到数据库
     *
     * @param admin
     */
    @Override
    public void addAdmin(Admin admin) {
        adminRepository.addAdmin(admin);
    }

    /**
     * 通过adminId删除admin对象
     *
     * @param adminId
     */
    @Override
    public void deleteAdminById(int adminId) {
        adminRepository.deleteAdminById(adminId);
    }

    /**
     * 通过新的admin对象来更新admin对象
     *
     * @param admin
     */
    @Override
    public void editAdmin(Admin admin) {
        adminRepository.editAdmin(admin);
    }

    /**
     * 通过userName找到admin对象
     *
     * @param userName
     * @return
     */
    @Override
    public List<Admin> searchAdminByUsername(String userName) {
        return adminRepository.searchAdminByUsername(userName);
    }


}
