package nwpu.threebull.forum.service;

import nwpu.threebull.forum.entity.Admin;

import java.util.List;

/**
 * AdminService的一些接口
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */


public interface AdminService {
    /**
     * 通过userName找到admin对象
     *
     * @param userName
     * @return
     */
    List<Admin> searchAdminByUsername(String userName);

    /**
     * 通过adminId找到admin对象
     * @param adminId
     * @return
     */
    Admin findAdminById(int adminId);

    /**
     * 通过userName，password找到admin对象
     * @param name
     * @param password
     * @return
     */
    Admin findAdminByAdminNameAndPassword(String name, String password);

    /**
     *  通过userName查找所有同名的admin
     * @param name
     * @return
     */
    Admin findAdminByAdminName(String name);

    /**
     * 找到所有的管理员
     * @return
     */
    List<Admin> findAllAdmins();

    /**
     * 通过admin对象将admin添加到数据库
     * @param admin
     */
    void addAdmin(Admin admin);

    /**
     * 通过adminId删除admin对象
     * @param adminId
     */
    void deleteAdminById(int adminId);

    /**
     * 通过新的admin对象来更新admin对象
     * @param admin
     */
    void editAdmin(Admin admin);
}
