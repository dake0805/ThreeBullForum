package nwpu.threebull.forum.dao;

import nwpu.threebull.forum.entity.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *关于AdminRepository的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public interface AdminRepository {
    /**
     * 通过adminId找到admin对象
     * @param adminId
     * @return
     */
    Admin findAdminById(int adminId);

    /**
     * 通过userName，password找到admin对象
     *
     * @param userName
     * @param password
     * @return
     */
    Admin findAdminByAdminNameAndPassword(String userName, String password);

    /**
     * 通过userName找到admin对象
     *
     * @param userName
     * @return
     */
    Admin findAdminByAdminName(String userName);

    /**
     * 通过userName查找所有同名的admin
     *
     * @param userName
     * @return
     */
    List<Admin> searchAdminByUsername(String userName);

    /**
     *
     * 找到所有的管理员
     *
     * @return
     */
    List<Admin> findAllAdmins();

    /**
     *
     * 通过admin对象将admin添加到数据库
     *
     * @param admin
     */
    void addAdmin(Admin admin);

    /**
     *
     * 通过adminId删除admin对象
     *
     * @param adminId
     */
    void deleteAdminById(int adminId);

    /**
     *
     * 通过新的admin对象来更新admin对象
     *
     * @param admin
     */
    void editAdmin(Admin admin);
}
