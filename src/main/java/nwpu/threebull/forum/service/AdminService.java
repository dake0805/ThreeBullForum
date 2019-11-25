package nwpu.threebull.forum.service;

import nwpu.threebull.forum.entity.Admin;

public interface AdminService {

    Admin findAdminByAdminNameAndPassword(String name, String password);
}
