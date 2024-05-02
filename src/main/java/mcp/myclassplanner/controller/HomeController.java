package mcp.myclassplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import mcp.myclassplanner.model.service.MemberService;

@Controller
public class HomeController {
//
//    private final MemberService memberService;
//
//    @Autowired
//    public HomeController(MemberService memberService){
//        this.memberService = memberService;
//    }

    @GetMapping("/")
    public String welcome(){
        return "home/welcome";
    }

    @GetMapping("/home")
    public String home() {
        return "home/home";
    }
    @GetMapping("/course")
    public String course() {
        return "course/course";
    }
    @GetMapping("/loading")
    public String loading() {
        return "common/loading";
    }

}
