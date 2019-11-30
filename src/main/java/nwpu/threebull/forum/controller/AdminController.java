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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * Admin相关的控制类
 * 请求路径包括/admin
 *
 * @author ThreeBullForumTeam
 * @version 1.0
 */
@Controller
//@SessionAttributes({"admin"})
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

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(Model model) {
        return "admin/login";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(Model model) {
        return "admin/home";
    }

    /***
     *
     * @param model
     * @param userName
     * @param password
     * @param pageNo
     * @param pageSize
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(Model model,
                               @RequestParam(value = "userName", defaultValue = "") String userName,
                               @RequestParam(value = "password", defaultValue = "") String password,
                               @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                               HttpSession session,
                               HttpServletRequest request, HttpServletResponse response) {
        Admin admin;
        admin = adminService.findAdminByAdminNameAndPassword(userName, password);
        if (null != admin) {
            boolean cookies_has = false;
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                if (cookies != null) {
                    for (int i = 0; i < cookies.length; i++) {
                        if (cookies[i].getName().equals("admin")) {
                            cookies[i].setValue(admin.getUserName());
                            cookies_has = true;
                        }
                    }
                }
            }
            if (!cookies_has) {
                Cookie cookie = new Cookie("admin", admin.getUserName());
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            }
            session.setAttribute("admin", admin);
            model.addAttribute("AllTopics", topicService.findPageTopics(pageNo, pageSize));
            return "homePage";
        }
        return "admin/loginError";
    }

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        session.invalidate();
        return "redirect:/";
    }

    // @RequestMapping(value = "/manageTopics", method = RequestMethod.GET)
    // public String manageTopics(Model model,
    //                            @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
    //                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {
    //     model.addAttribute("AllTopics", topicService.findPageTopics(pageNo, pageSize));
    //     return "admin/manageTopics";
    // }

    /**
     *
     * @param info
     * @param model
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
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

    /**
     *
     * @param topicId
     * @param model
     * @param pageNo
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping(value = "/deleteTopic/{topicId}", method = RequestMethod.GET)
    public String deleteTopic(@PathVariable("topicId") int topicId, Model model,
                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                              HttpSession session) {

        Topic topic = topicService.findByTopicId(topicId);
        if (null != topic) {
            topicService.deleteTopic(topicId);
        }
        return "redirect:/";
    }

    /**
     *
     * @param topicId
     * @param model
     * @param pageNo
     * @param pageSize
     * @param session
     * @return
     */
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

    /**
     *
     * @param topicId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/topTopic/{topicId}", method = RequestMethod.GET)
    public String topTopic(@PathVariable("topicId") int topicId, Model model,
                           HttpSession session) {

        Topic topic = topicService.findByTopicId(topicId);
        if (null != topic) {
            topicService.topTopic(topicId);
        }
        return "redirect:/";
    }

    /**
     *
     * @param topicId
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/unTopTopic/{topicId}", method = RequestMethod.GET)
    public String unTopTopic(@PathVariable("topicId") int topicId, Model model,
                             HttpSession session) {

        Topic topic = topicService.findByTopicId(topicId);
        if (null != topic) {
            topicService.unTopTopic(topicId);
        }
        return "redirect:/";
    }

    /**
     *
     * @param topicId
     * @param model
     * @param pageNo
     * @param pageSize
     * @param session
     * @return
     */
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

    /**
     *
     * @param topicId
     * @param title
     * @param content
     * @param model
     * @return
     */
    @RequestMapping(value = "/editTopic/{topicId}", method = RequestMethod.POST)
    public String get(@PathVariable("topicId") int topicId, @RequestParam(value = "title", defaultValue = "") String title,
                      @RequestParam(value = "content", defaultValue = "") String content, Model model) {
        if (title.length() > 0 && content.length() > 0) {
            topicService.updateTitleByTopicId(topicId, title, content);
            Topic topic = topicService.findByTopicId(topicId);
            if (null != topic) {
                model.addAttribute("singleTopic", topic);
                model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), 1, 10));
                return "redirect:/topic/detail/" + topic.getId();
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/user/editTopic/{topicId}?info=empty_titleOrContent";
        }
    }

    /**
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/manageUsers", method = RequestMethod.GET)
    public String manageUsers(Model model, HttpSession session) {
        model.addAttribute("AllUsers", userService.findAllUsers());
        return "admin/manageUsers";
    }

    /**
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/lockUser/{userId}", method = RequestMethod.GET)
    public String lockUser(@PathVariable("userId") int userId, Model model) {
        userService.lockUserById(userId);
        return "redirect:/admin/manageUsers";
    }

    /**
     *
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/unLockUser/{userId}", method = RequestMethod.GET)
    public String unLockUser(@PathVariable("userId") int userId, Model model) {
        userService.unLockUserById(userId);
        return "redirect:/admin/manageUsers";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/manageAdmins", method = RequestMethod.GET)
    public String manageAdmins(Model model) {
        List<Admin> admins = adminService.findAllAdmins();
        model.addAttribute("AdminList", admins);
        return "admin/manageAdmin";
    }

    /**
     *
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/addAdmin", method = RequestMethod.GET)
    public String addAdmin(Model model, HttpSession httpSession) {
        return "admin/addAdmin";
    }

    /**
     *
     * @param username
     * @param password
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addAdmin(@RequestParam(value = "username", defaultValue = "") String username,
                           @RequestParam(value = "password", defaultValue = "") String password,
                           HttpServletResponse response) throws IOException {

        if (adminService.findAdminByAdminName(username) != null) {
            return "redirect:/admin/manageAdmins?info=userNameExist";
        }

        Admin admin = new Admin(0, username, password);
        adminService.addAdmin(admin);
        return "redirect:/admin/manageAdmins";
    }

    /**
     *
     * @param adminId
     * @param session
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/deleteAdmin/{adminId}", method = RequestMethod.GET)
    public String deleteAdmin(@PathVariable(value = "adminId") int adminId,
                              HttpSession session,
                              HttpServletResponse response) throws IOException {
        Admin currentAdmin = (Admin) session.getAttribute("admin");
        if (currentAdmin.getId() == adminId) {
            return "redirect:/admin/manageAdmins?info=deleteSelf";
        }
        adminService.deleteAdminById(adminId);
        return "redirect:/admin/manageAdmins";
    }

    /**
     *
     * @param adminId
     * @param model
     * @return
     */
    @RequestMapping(value = "/editAdmin/{adminId}", method = RequestMethod.GET)
    public String editAdmin(@PathVariable(value = "adminId") int adminId, Model model) {
        Admin admin = adminService.findAdminById(adminId);
        model.addAttribute("SelectedAdmin", admin);
        return "admin/editAdmin";
    }

    /**
     *
     * @param adminId
     * @param username
     * @param password
     * @param model
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/editAdmin/{adminId}", method = RequestMethod.POST)
    public String editAdmin(@PathVariable(value = "adminId") int adminId,
                            @RequestParam(value = "username", defaultValue = "") String username,
                            @RequestParam(value = "password", defaultValue = "") String password,
                            Model model,
                            HttpServletResponse response) throws IOException {
        Admin admin = adminService.findAdminById(adminId);

        if (adminService.findAdminByAdminName(username) != null) {
            // response.setContentType("text/html; charset=utf-8");
            // PrintWriter out = response.getWriter();
            // out.println("<script>alert(\"Username already exist!\");</script>");
            return "redirect:/admin/manageAdmins?info=userNameExist";
        }

        admin.setUserName(username);
        admin.setPassword(password);
        adminService.editAdmin(admin);
        return "redirect:/admin/manageAdmins";
    }

    /**
     *
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchAdmin", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchAdmin(@RequestParam(value = "username") String username, Model model) {
        model.addAttribute("searchAdmins", adminService.searchAdminByUsername(username));
        return "/admin/searchAdmin";
    }

}
