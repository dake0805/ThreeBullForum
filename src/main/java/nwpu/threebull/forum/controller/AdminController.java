package nwpu.threebull.forum.controller;

import nwpu.threebull.forum.entity.Admin;
import nwpu.threebull.forum.entity.Topic;
import nwpu.threebull.forum.service.AdminService;
import nwpu.threebull.forum.service.ReplyService;
import nwpu.threebull.forum.service.TopicService;
import nwpu.threebull.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes({"admin"})
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(Model model) {
        return "admin/login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(Model model) {
        return "admin/home";
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

    @RequestMapping(value = "/searchTopic", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchTopic(@RequestParam(value = "info") String info, Model model,
                              @RequestParam(value = "type") String type,
                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("searchTopics", topicService.findPageByTopicTitleOrContent(info, type, pageNo, pageSize));
        model.addAttribute("info", info);
        model.addAttribute("type", type);
        return "/admin/searchTopic";
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

    @RequestMapping(value = "/topic/{topicId}", method = RequestMethod.GET)
    public String getTopic(@PathVariable("topicId") int topicId, Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {

        Topic topic = topicService.findByTopicId(topicId);
        if (null != topic) {
            topicService.updateClickNumByTopic(topic);
            model.addAttribute("singleTopic", topic);
            model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), pageNo, pageSize));
            return "admin/topic";
        } else {
            return "redirect:/admin/manageTopics";
        }
    }

    @RequestMapping(value = "/topTopic/{topicId}", method = RequestMethod.GET)
    public String topTopic(@PathVariable("topicId") int topicId, Model model,
                           HttpSession session) {

        Topic topic = topicService.findByTopicId(topicId);
        if (null != topic) {
            topicService.topTopic(topicId);
        }
        return "redirect:/admin/manageTopics";
    }

    @RequestMapping(value = "/editTopic/{topicId}", method = RequestMethod.GET)
    public String editTopic(@PathVariable("topicId") int topicId, Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {

        Topic topic = topicService.findByTopicId(topicId);
        if (null != topic) {
            model.addAttribute("singleTopic", topic);
            model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), pageNo, pageSize));
            return "admin/editTopic";
        } else {
            return "redirect:/admin/manageTopics";
        }

    }

    @RequestMapping(value = "/editTopic/{topicId}", method = RequestMethod.POST)
    public String editTopic(@PathVariable("topicId") int topicId, @RequestParam(value = "title", defaultValue = "") String title,
                            @RequestParam(value = "content", defaultValue = "") String content, Model model) {
        topicService.updateTitleByTopicId(topicId, title, content);
        Topic topic = topicService.findByTopicId(topicId);
        if (null != topic) {
            model.addAttribute("singleTopic", topic);
            model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), 1, 10));
            return "admin/topic";
        } else {
            return "redirect:/admin/manageTopics";
        }
    }

    @RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
    public String manageUsers(Model model, HttpSession session) {
        model.addAttribute("AllUsers", userService.findAllUsers());
        return "admin/manageUsers";
    }

    @RequestMapping(value = "/lockUser/{userId}", method = RequestMethod.GET)
    public String lockUser(@PathVariable("userId") int userId, Model model) {
        // if (null != topic) {
        //     model.addAttribute("singleTopic", topic);
        //     model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), 1, 10));
        //     return "admin/topic";
        // } else {
        //     return "redirect:/admin/manageTopics";
        // }
        userService.lockUserById(userId);
        return "redirect:/admin/manageUsers";
    }

    @RequestMapping(value = "/manageAdmins", method = RequestMethod.GET)
    public String manageAdmins(Model model) {
        List<Admin> admins = adminService.findAllAdmins();
        model.addAttribute("AdminList", admins);
        return "admin/manageAdmin";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.GET)
    public String addAdmin(Model model, HttpSession httpSession) {
        return "admin/addAdmin";
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addAdmin(@RequestParam(value = "username", defaultValue = "") String username,
                           @RequestParam(value = "password", defaultValue = "") String password) {
        Admin admin = new Admin(0, username, password);
        adminService.addAdmin(admin);
        return "redirect:/admin/manageAdmins";
    }

    @RequestMapping(value = "/deleteAdmin/{adminId}", method = RequestMethod.GET)
    public String deleteAdmin(@PathVariable(value = "adminId") int adminId) {
        adminService.deleteAdminById(adminId);
        return "redirect:/admin/manageAdmins";
    }

    @RequestMapping(value = "/editAdmin/{adminId}", method = RequestMethod.GET)
    public String editAdmin(@PathVariable(value = "adminId") int adminId, Model model) {
        Admin admin = adminService.findAdminById(adminId);
        model.addAttribute("SelectedAdmin", admin);
        return "admin/editAdmin";
    }

    @RequestMapping(value = "/editAdmin/{adminId}", method = RequestMethod.POST)
    public String editAdmin(@PathVariable(value = "adminId") int adminId,
                            @RequestParam(value = "username", defaultValue = "") String username,
                            @RequestParam(value = "password", defaultValue = "") String password,
                            Model model) {
        Admin admin = adminService.findAdminById(adminId);
        admin.setUserName(username);
        admin.setPassword(password);
        adminService.editAdmin(admin);
        return "redirect:/admin/manageAdmins";
    }

    @RequestMapping(value = "/searchAdmin", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchAdmin(@RequestParam(value = "username") String username, Model model) {
        model.addAttribute("searchAdmins", adminService.searchAdminByUsername(username));
        return "/admin/searchAdmin";
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
