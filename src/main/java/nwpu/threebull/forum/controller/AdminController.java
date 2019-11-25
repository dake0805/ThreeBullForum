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
    public String get(@PathVariable("topicId") int topicId, @RequestParam(value = "title", defaultValue = "") String title,
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
