package nwpu.threebull.forum.controller;

import nwpu.threebull.forum.entity.Topic;
import org.springframework.stereotype.Controller;
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
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/")
public class VisitorController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("AllTopics", topicService.findPageTopics(pageNo, pageSize));
        return "homePage";
    }
//
//    @RequestMapping(value = "/mytopics", method = RequestMethod.GET)
//    public String showMyTopics(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
//                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {
//
//        User user = (User) session.getAttribute("user");
//        if (null != user) {
//            model.addAttribute("MyTopics", topicService.findPageByUserId(user.getId(), pageNo, pageSize));
//            return "user/mytopics";
//        } else {
//            return "redirect:/";
//        }
//    }
//
//
//    @RequestMapping(value = "/newtopic", method = RequestMethod.GET)
//    public String newTopics(Model model, HttpSession httpSession,
//                            @RequestParam(value = "info", required = false) String info) {
//        User user = (User) httpSession.getAttribute("user");
//        //normal
////        if (null != user) {
////            return "user/newtopic";
////        } else {
////            return "redirect:/user/newtopic";
////        }
//        //异常操作
//        if (info != null) {
//            model.addAttribute(info);
//        }
//        return "user/newtopic";
//
//    }
//
//    @RequestMapping(value = "/newtopic", method = RequestMethod.POST)
//    public String creatNewTopics(Model model, HttpSession httpSession,
//                                 @RequestParam(value = "title", defaultValue = "") String title,
//                                 @RequestParam(value = "content", defaultValue = "") String content) {
//        User user = (User) httpSession.getAttribute("user");
//        Date date = new Date();
//        Timestamp timestamp = new Timestamp(date.getTime());
//        if (title.length() > 0 && content.length() > 0) {
//            Topic topic = new Topic(0, title, content, user, false, null, timestamp, 0, 0);
//            topicService.newTopic(topic);
//            return "redirect:/user/home";
//        } else {
//            return "redirect:/user/newtopic?info=empty_titleOrContent";
//        }
//
//    }
//
//
//    @RequestMapping(value = "/topic/{topicId}", method = RequestMethod.GET)
//    public String getTopic(@PathVariable("topicId") int topicId, Model model, HttpSession session,
//                           @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
//                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
//                           @RequestParam(value = "info", required = false) String info) {
//        if (info != null) {
//            model.addAttribute(info);
//        }
//        User user = (User) session.getAttribute("user");
//        Topic topic = topicService.findByTopicId(topicId);
//        if (null != topic) {
//            topicService.updateClickNumByTopic(topic);
//            if (user.equals(topic.getUser())) {
//                model.addAttribute("isMyself", true);
//            } else {
//                model.addAttribute("isMyself", false);
//            }
//            model.addAttribute("singleTopic", topic);
//            model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), pageNo, pageSize));
//            return "user/topic";
//        } else {
//            return "redirect:/user/home";
//        }
//
//    }
//
//    @RequestMapping(value = "/editTopic/{topicId}", method = RequestMethod.GET)
//    public String editTopic(@PathVariable("topicId") int topicId, Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
//                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session) {
//
//        Topic topic = topicService.findByTopicId(topicId);
//        if (null != topic) {
//            model.addAttribute("singleTopic", topic);
//            model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), pageNo, pageSize));
//            return "user/editTopic";
//        } else {
//            return "redirect:/user/home";
//        }
//
//    }
//
//    @RequestMapping(value = "/editTopic/{topicId}", method = RequestMethod.POST)
//    public String get(@PathVariable("topicId") int topicId, @RequestParam(value = "title", defaultValue = "") String title,
//                      @RequestParam(value = "content", defaultValue = "") String content, Model model) {
//        if (title.length() > 0 && content.length() > 0) {
//            topicService.updateTitleByTopicId(topicId, title, content);
//            Topic topic = topicService.findByTopicId(topicId);
//            if (null != topic) {
//                model.addAttribute("singleTopic", topic);
//                model.addAttribute("replys", replyService.findPageByTopicId(topic.getId(), 1, 10));
//                model.addAttribute("isMyself", true);
//                return "user/topic";
//            } else {
//                return "redirect:/";
//            }
//        } else {
//            return "redirect:/user/editTopic/{topicId}?info=empty_titleOrContent";
//        }
//
//    }
//
//    @RequestMapping(value = "/searchTopic", method = {RequestMethod.POST, RequestMethod.GET})
//    public String searchTopic(@RequestParam(value = "info") String info, Model model,
//                              @RequestParam(value = "type") String type,
//                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
//                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//        model.addAttribute("searchTopics", topicService.findPageByTopicTitleOrContent(info, type, pageNo, pageSize));
//        model.addAttribute("info", info);
//        model.addAttribute("type", type);
//        return "/user/searchTopic";
//    }
//
//
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpSession session) {
//        session.removeAttribute("user");
//        session.invalidate();
//        return "redirect:/";
//    }

}
