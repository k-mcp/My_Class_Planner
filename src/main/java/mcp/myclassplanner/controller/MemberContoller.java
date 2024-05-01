package mcp.myclassplanner.controller;

import mcp.myclassplanner.model.dto.MemberDTO;
import mcp.myclassplanner.model.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class MemberContoller {

    private final MemberService memberService;

    public MemberContoller(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signin")
    public String login(){
        return "auth/signin";
    }
    @PostMapping("/signIn")
    public String signInCheck(String username, String password){
        int result = memberService.signIn(username,password);
        return "home";

    }

    @GetMapping("/signup")
    public String signup(){
        return "auth/signup";
    }
    @PostMapping("/signup")
    public String singupPro(MemberDTO memberDTO){
        memberService.signUp(memberDTO);
        return "redirect:/signin";
    }
}
