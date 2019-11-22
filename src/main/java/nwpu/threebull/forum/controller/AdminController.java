package nwpu.threebull.forum.controller;

import nwpu.threebull.forum.dao.AdminRepository;
import nwpu.threebull.forum.entity.Admin;
import nwpu.threebull.forum.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/admin")
public class AdminController {
//    @Autowired
//    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/SelectAdmin")
    public String selectAdmin(Model model) throws IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");

//        long personId = Long.parseLong(request.getParameter("id"));
//        Person person = personService.findPersonById(personId);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        response.getWriter().write(mapper.writeValueAsString(person));
//        response.getWriter().close();

        int adminId = 1;
        Admin admin = adminService.findAdminById(adminId);

        if (admin != null) {
            model.addAttribute(admin);
            return "admin/AdminProfile";
        } else {
            return "redirect:/";
        }

    }


}