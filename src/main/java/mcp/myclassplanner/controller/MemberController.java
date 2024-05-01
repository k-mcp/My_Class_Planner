package mcp.myclassplanner.controller;

import mcp.myclassplanner.model.dto.MemberDTO;
import mcp.myclassplanner.model.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signin")
    public String login(){
        return "auth/signin";
    }
    @PostMapping("/signin")
    public String signInCheck(String username, String password){
        int result = memberService.signIn(username,password);
        return "home";

    }

    @GetMapping("/signup")
    public String signup(){
        return "auth/signup";
    }
    @PostMapping("/signup")
    public ModelAndView signupPro(MemberDTO memberDTO, ModelAndView mv){
        String message = "";
        int error = memberService.signUpError(memberDTO);
        if(error == 1){ // username exists
            message = "username already exists";
            mv.addObject("message", message);
            return mv;
        } else if(error == 2){ // email exists
            message = "email has already taken. please sign in with your id";
            mv.addObject("message", message);
            return mv;
        }

        memberService.signUp(memberDTO);

        message ="We sent a verification link to your email. Please verify your email and sign in!";
        mv.addObject("message", message);
        mv.addObject("email", memberDTO.getEmail());
        return mv;

    }
    @GetMapping("/signUpConfirm")
    public String signupConfirm(String email, String authKey) {
        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("authKey", authKey);

        int auth = memberService.authorize(map);
        if(auth == 0){ // if error occurs
            return "failVerifying";
        }
        else { // if succeed
            return "verified";

        }


    }

}
