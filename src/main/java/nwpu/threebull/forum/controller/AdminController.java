package nwpu.threebull.forum.controller;

import nwpu.threebull.forum.dao.AdminRepository;
import nwpu.threebull.forum.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
private AdminRepository adminRepository;

}
