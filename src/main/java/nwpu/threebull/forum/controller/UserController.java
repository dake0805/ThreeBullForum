package nwpu.threebull.forum.controller;


import nwpu.threebull.forum.dao.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/User")
public class UserController {
    private UserRepository userRepository;
}
