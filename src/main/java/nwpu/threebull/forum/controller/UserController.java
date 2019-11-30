package nwpu.threebull.forum.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import nwpu.threebull.forum.entity.Reply;
import nwpu.threebull.forum.entity.Topic;
import nwpu.threebull.forum.entity.User;
import nwpu.threebull.forum.service.ReplyService;
import nwpu.threebull.forum.service.TopicService;
import nwpu.threebull.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * user相关的控制类
 * 前置路径为“/user”
 *
 * @author ThreeBullForumTeam
 * @version 1.0
 */
@Controller
//@SessionAttributes({"user"})
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new User());
        return "user/register";
    }

    /**
     *
     * @param response
     * @param user
     * @param bindingResult
     * @param session
     * @param rePassword
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(HttpServletResponse response,
                                      @Valid @ModelAttribute User user,
                                      BindingResult bindingResult,
                                      HttpSession session,
                                      @RequestParam(value = "repass", defaultValue = "") String rePassword, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            return "user/register";
        }
        if (!user.getPassword().equals(rePassword)) {
//            response.setContentType("text/html; charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('repassword');</script>");
            return "redirect:/user/register?info=repassword";
        }
        if (userService.findUserByUserName(user.getUserName()) != null) {
//            response.setContentType("text/html; charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('same name');</script>");
            return "redirect:/user/register?info=samename";
        }
        user.setId(0);
        userService.addUser(user);
        user = userService.findUserByUserName(user.getUserName());
        session.setAttribute("user", user);
        model.addAttribute("AllTopics", topicService.findPageTopics(1, 10));
        return "homePage";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(Model model) {
        return "user/login";
    }

    /**
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
    public String processLogin(Model model, @RequestParam(value = "userName", defaultValue = "") String userName,
                               @RequestParam(value = "password", defaultValue = "") String password, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session,
                               HttpServletRequest request, HttpServletResponse response) {
        User user;
        user = userService.findUserByUserNameAndPassword(userName, password);
        if (null != user) {
            boolean judge = true;
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("user")) {
                        cookies[i].setValue(user.getUserName());
                        judge = false;
                    }
                }
            }
            if (judge) {
                Cookie cookie = new Cookie("user", user.getUserName());
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
            }
//            model.addAttribute(user);
            session.setAttribute("user", user);
            model.addAttribute("AllTopics", topicService.findPageTopics(pageNo, pageSize));
            return "redirect:/";
        }
        return "/user/loginError";
    }

    /**
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("AllTopics", topicService.findPageTopics(pageNo, pageSize));
        return "user/home";
    }

    /**
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param session
     * @return
     */
    @RequestMapping(value = "/mytopics", method = RequestMethod.GET)
    public String showMyTopics(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (null != user) {
            model.addAttribute("MyTopics", topicService.findPageByUserId(user.getId(), pageNo, pageSize));
            return "user/mytopics";
        } else {
            return "redirect:/";
        }
    }

    /**
     *
     * @param model
     * @param httpSession
     * @param info
     * @return
     */
    @RequestMapping(value = "/newtopic", method = RequestMethod.GET)
    public String newTopics(Model model, HttpSession httpSession,
                            @RequestParam(value = "info", required = false) String info) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            if (info != null) {
                model.addAttribute(info);
            }
            return "user/newtopic";
        } else {
            return "user/login";
        }

    }

    /**
     *
     * @param model
     * @param httpSession
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(value = "/newtopic", method = RequestMethod.POST)
    public String creatNewTopics(Model model, HttpSession httpSession,
                                 @RequestParam(value = "title", defaultValue = "") String title,
                                 @RequestParam(value = "content", defaultValue = "") String content) {
        User user = (User) httpSession.getAttribute("user");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        if (!user.getIsLocked()) {
            Topic topic = new Topic(0, title, content, user, false, null, timestamp, 0, 0);
            topicService.newTopic(topic);
            return "redirect:/";
        } else {
            return "redirect:/?info=user_locked";
        }

    }

    /**
     *
     * @param topicId
     * @param model
     * @param session
     * @param pageNo
     * @param pageSize
     * @param info
     * @return
     */
    @RequestMapping(value = "/topic/{topicId}", method = RequestMethod.GET)
    public String getTopic(@PathVariable("topicId") int topicId, Model model, HttpSession session,
                           @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                           @RequestParam(value = "info", required = false) String info) {
        if (info != null) {
            model.addAttribute(info);
        }
        User user = (User) session.getAttribute("user");
        Topic topic = topicService.findByTopicId(topicId);
        if (null != topic) {
            topicService.updateClickNumByTopic(topic);
            if (user.equals(topic.getUser())) {
                model.addAttribute("isMyself", true);
            } else {
                model.addAttribute("isMyself", false);
            }
            model.addAttribute("singleTopic", topic);
            model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), pageNo, pageSize));
            return "user/topic";
        } else {
            return "redirect:/user/home";
        }

    }

    /**
     *
     * @param model
     * @param session
     * @param content
     * @param topicId
     * @return
     */
    @RequestMapping(value = "/topic/{topicId}", method = RequestMethod.POST)
    public String newReply(Model model, HttpSession session,
                           @RequestParam(value = "content", defaultValue = "") String content,
                           @PathVariable int topicId) {
        User user = (User) session.getAttribute("user");
        Topic topic = topicService.findByTopicId(topicId);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        if (!user.getIsLocked()) {
            Reply reply = new Reply(0, topic.getId(), content, user, timestamp);
            replyService.newReply(reply);
            return "redirect:/topic/detail/{topicId}";
        } else {
            return "redirect:/topic/detail/{topicId}?info=user_locked";
        }

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
            return "user/editTopic";
        } else {
            return "redirect:/user/home";
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
                model.addAttribute("isMyself", true);
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
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("MyTopics", topicService.findPageByMyTopicTitleOrContent(info, user.getId(), type, pageNo, pageSize));

        model.addAttribute("info", info);
        model.addAttribute("type", type);
        return "/user/mytopics";
    }

    /**
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/editUser", method = GET)
    public String editUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("SelectedUser", user);
        return "user/editUser";
    }

    @RequestMapping(value = "/editUser", method = POST)
    public String editUser(@RequestParam(value = "username", defaultValue = "") String username,
                           @RequestParam(value = "password", defaultValue = "") String password,
                           @RequestParam(value = "newPassword", defaultValue = "") String newPassword,
                           Model model, HttpSession session,
                           HttpServletResponse response) throws IOException {
        User user = (User) session.getAttribute("user");
        if (!user.getPassword().equals(password)) {
            return "redirect:editUser";
        }
        user.setUserName(username);
        user.setPassword(newPassword);
        userService.editUser(user);
        session.setAttribute("user", user);
        return "redirect:/";
    }
}
