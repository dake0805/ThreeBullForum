package nwpu.threebull.forum.controller;

import nwpu.threebull.forum.entity.Admin;
import nwpu.threebull.forum.entity.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import nwpu.threebull.forum.service.UserService;
import nwpu.threebull.forum.service.TopicService;
import nwpu.threebull.forum.service.ReplyService;
import nwpu.threebull.forum.entity.User;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/topic")

public class TopicController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    @RequestMapping(value = "/detail/{topicId}", method = RequestMethod.GET)
    public String getTopic(@PathVariable("topicId") int topicId, Model model, HttpSession session,
                           @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                           @RequestParam(value = "info", required = false) String info) {
        if (info != null) {
            model.addAttribute(info);
        }
        User user = (User) session.getAttribute("user");
        Admin admin = (Admin) session.getAttribute("admin");
        if (user == null && admin == null) {
            Topic topic = topicService.findByTopicId(topicId);
            if (null != topic) {
                topicService.updateClickNumByTopic(topic);
                model.addAttribute("singleTopic", topic);
                model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), pageNo, pageSize));
                return "topic";
            } else {
                return "homePage";
            }
        } else if (user != null) {
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
                return "topic";
            } else {
                return "homePage";
            }
        } else if (admin != null) {
            //TODO
            return "homePage";
        }
        return "homePage";
    }
}
