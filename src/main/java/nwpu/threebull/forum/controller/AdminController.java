package nwpu.threebull.forum.controller;

import nwpu.threebull.forum.dao.AdminRepository;
import nwpu.threebull.forum.entity.Admin;
import nwpu.threebull.forum.entity.Topic;
import nwpu.threebull.forum.service.AdminService;
import nwpu.threebull.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@SessionAttributes({"admin"})
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(Model model) {
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(Model model,
                               @RequestParam(value = "userName", defaultValue = "") String userName,
                               @RequestParam(value = "password", defaultValue = "") String password,
                               HttpSession session) {
        Admin admin;
        admin = adminService.findAdminByAdminNameAndPassword(userName, password);
        if (null != admin) {
            // session.setAttribute("current_user", admin);
            model.addAttribute(admin);
            return "admin/home";
        }
        return "admin/loginError";
    }

    @RequestMapping(value = "/manageTopics", method = RequestMethod.GET)
    public String manageTopics(Model model,
                               @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {
        model.addAttribute("AllTopics", topicService.findPageTopics(pageNo, pageSize));
        return "admin/manageTopics";
    }

    @RequestMapping(value = "/deleteTopic/{topicId}", method = RequestMethod.GET)
    public String deleteTopic(@PathVariable("topicId") int topicId, Model model,
                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                              HttpSession session) {

        Topic topic = topicService.findByTopicId(topicId);
        if (null != topic) {
            topicService.deleteTopic(topicId);
        }
        return "redirect:/admin/manageTopics";
    }
    // @RequestMapping("/SelectAdmin")
    // public String selectAdmin(Model model) throws IOException {
    //    request.setCharacterEncoding("utf-8");
    //    response.setCharacterEncoding("utf-8");

    // long personId = Long.parseLong(request.getParameter("id"));
    // Person person = personService.findPersonById(personId);

    // ObjectMapper mapper = new ObjectMapper();

    // response.getWriter().write(mapper.writeValueAsString(person));
    // response.getWriter().close();
    //
    // int adminId = 1;
    // Admin admin = adminService.findAdminById(adminId);
    //
    // if (admin != null) {
    //     model.addAttribute(admin);
    //     return "admin/AdminProfile";
    // } else {
    //     return "redirect:/";
    // }

    // }
}
