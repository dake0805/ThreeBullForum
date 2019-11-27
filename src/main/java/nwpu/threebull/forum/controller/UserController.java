package nwpu.threebull.forum.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import nwpu.threebull.forum.entity.Reply;
import nwpu.threebull.forum.entity.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import nwpu.threebull.forum.service.UserService;
import nwpu.threebull.forum.service.TopicService;
import nwpu.threebull.forum.service.ReplyService;
import nwpu.threebull.forum.entity.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;


    @RequestMapping(value = "/topic/{topicId}", method = RequestMethod.POST)
    public String newReply(Model model, HttpSession session,
                           @RequestParam(value = "content", defaultValue = "") String content,
                           @PathVariable int topicId) {
        User user = (User) session.getAttribute("user");
        Topic topic = topicService.findByTopicId(topicId);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        if (content.trim().length() > 0) {
            Reply reply = new Reply(0, topic.getId(), content, user, timestamp);
            replyService.newReply(reply);

            return "redirect:/user/topic/{topicId}";
        } else {
            return "redirect:/user/topic/{topicId}?info=empty_content";
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute(new User());
        return "user/register";
    }

    @RequestMapping(value = "/register", method = POST)
    //TODO jsp中三个参数 多了一个确认密码参数 进行确认
    public String processRegistration(@Valid @ModelAttribute User user, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "user/register";
        }
        user.setId(0);
        userService.addUser(user);

        return "redirect:/user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(Model model) {
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(Model model, @RequestParam(value = "userName", defaultValue = "") String userName,
                               @RequestParam(value = "password", defaultValue = "") String password, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {
        User user;
        user = userService.findUserByUserNameAndPassword(userName, password);
        if (null != user) {
            model.addAttribute(user);
            model.addAttribute("AllTopics", topicService.findPageTopics(pageNo, pageSize));
            return "homePage";
        }
        return "/user/loginError";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showHome(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("AllTopics", topicService.findPageTopics(pageNo, pageSize));
        return "user/home";
    }

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


    @RequestMapping(value = "/newtopic", method = RequestMethod.GET)
    public String newTopics(Model model, HttpSession httpSession,
                            @RequestParam(value = "info", required = false) String info) {
        User user = (User) httpSession.getAttribute("user");
        if (info != null) {
            model.addAttribute(info);
        }
        return "user/newtopic";

    }

    @RequestMapping(value = "/newtopic", method = RequestMethod.POST)
    public String creatNewTopics(Model model, HttpSession httpSession,
                                 @RequestParam(value = "title", defaultValue = "") String title,
                                 @RequestParam(value = "content", defaultValue = "") String content) {
        User user = (User) httpSession.getAttribute("user");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        if (title.trim().length() > 0 && content.trim().length() > 0) {
            Topic topic = new Topic(0, title, content, user, false, null, timestamp, 0, 0);
            topicService.newTopic(topic);
            return "redirect:/user/home";
        } else {
            return "redirect:/user/newtopic?info=empty_titleOrContent";
        }

    }


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
                return "user/topic";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/user/editTopic/{topicId}?info=empty_titleOrContent";
        }

    }

    @RequestMapping(value = "/searchTopic", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchTopic(@RequestParam(value = "info") String info, Model model,
                              @RequestParam(value = "type") String type,
                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("searchTopics", topicService.findPageByTopicTitleOrContent(info, type, pageNo, pageSize));
        model.addAttribute("info", info);
        model.addAttribute("type", type);
        return "/user/searchTopic";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }

}
