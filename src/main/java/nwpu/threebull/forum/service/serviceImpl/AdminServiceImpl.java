package nwpu.threebull.forum.service.serviceImpl;

import nwpu.threebull.forum.dao.AdminRepository;
import nwpu.threebull.forum.entity.Admin;
import nwpu.threebull.forum.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin findAdminByAdminNameAndPassword(String userName, String password) {
        return adminRepository.findAdminByAdminNameAndPassword(userName, password);
    }

}
