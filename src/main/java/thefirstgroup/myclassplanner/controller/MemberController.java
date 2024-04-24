package thefirstgroup.myclassplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thefirstgroup.myclassplanner.model.service.MemberService;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }



    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam("email") String email, @RequestParam("password") String password){

        return "signup";
    }
}
