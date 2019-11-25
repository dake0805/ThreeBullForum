package nwpu.threebull.forum.dao;

import nwpu.threebull.forum.entity.Admin;
import org.springframework.stereotype.Repository;

public interface AdminRepository {
    Admin findAdminByAdminNameAndPassword(String userName, String password);
}
