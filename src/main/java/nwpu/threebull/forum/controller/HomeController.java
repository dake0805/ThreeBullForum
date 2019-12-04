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

/**
 * Home的控制类
 * 前置路径为“/”
 *
 * @author ThreeBullForumTeam
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    /**
     * 获得主页
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param info
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                            @RequestParam(value = "info", required = false) String info) {
        if (info != null) {
            model.addAttribute(info);
        }
        model.addAttribute("AllTopics", topicService.findPageTopics(pageNo, pageSize));
        model.addAttribute("info");
        return "homePage";
    }

    /**
     * 搜索主题
     *
     * @param info
     * @param model
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "searchTopic", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchTopic(@RequestParam(value = "info") String info, Model model,
                              @RequestParam(value = "type") String type,
                              @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("searchTopics", topicService.findPageByTopicTitleOrContent(info, type, pageNo, pageSize));
        model.addAttribute("info", info);
        model.addAttribute("type", type);
        return "searchTopic";
    }
}
