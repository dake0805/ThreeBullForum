package nwpu.threebull.forum.controller;

import nwpu.threebull.forum.entity.Reply;
import nwpu.threebull.forum.entity.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import nwpu.threebull.forum.service.UserService;
import nwpu.threebull.forum.service.TopicService;
import nwpu.threebull.forum.service.ReplyService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import nwpu.threebull.forum.entity.User;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

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


    @RequestMapping(value = "/topic/{topicId}",method = RequestMethod.POST)
    public String  newReply(Model model, HttpSession session,
                            @RequestParam(value = "content", defaultValue = "") String content,
                            @PathVariable int topicId){
        User user=(User)session.getAttribute("user");
        Topic topic = topicService.findByTopicId(topicId);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Reply reply=new Reply(0,topic.getId(),content,user,timestamp);
        replyService.newReply(reply);
        return "redirect:/user/topic/{topicId}";
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
            return "user/home";
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
    public String newTopics(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (null != user) {
            return "user/newtopic";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/newtopic", method = RequestMethod.POST)
    public String creatNewTopics(Model model, HttpSession httpSession,
                                 @RequestParam(value = "title", defaultValue = "") String title,
                                 @RequestParam(value = "content", defaultValue = "") String content) {
        User user = (User) httpSession.getAttribute("user");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Topic topic = new Topic(0, title, content, user, false, null, timestamp, 0, 0);
        topicService.newTopic(topic);
        return "/user/home";
    }



    @RequestMapping(value = "/topic/{topicId}", method = RequestMethod.GET)
    public String getTopic(@PathVariable("topicId") int topicId, Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {

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
    }

    @RequestMapping(value = "/searchTopic", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchTopic(@RequestParam(value = "info") String info, Model model,
                              @RequestParam(value = "type") String type,
                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("searchTopics", topicService.findPageByTopicTitleOrContent(info, type, pageNo, pageSize));
        model.addAttribute("info", info);
        model.addAttribute("type", type);
        return "user/searchTopic";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/";
    }

}
