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

}
