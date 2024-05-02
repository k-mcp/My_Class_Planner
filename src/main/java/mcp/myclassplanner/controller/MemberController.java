package mcp.myclassplanner.controller;

import mcp.myclassplanner.model.dto.MemberDTO;
import mcp.myclassplanner.model.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void signIn(){

    }
    @PostMapping("/signin")
    public String signInCheck(String username, String password){
       System.out.println("username = " + username);
       System.out.println("password = " + password);  
       Map<String,String> map = new HashMap<>();
       map.put("username", username);
       map.put("password", password);

        int result = memberService.signIn(username,password);
        System.out.println("result = " + result);
        int check = memberService.check(username);
        System.out.println("check = " + check);
        if(result == 1 && check == 1){
            return "/home/home";
        } else {
            return "/auth/signin";
        }

    }

    @PostMapping("/signinWithAuth")
    public String signInCheckWithAuth(String username, String password){
        int result = memberService.signInWithAuth(username, password);
        if(result == 1) {
            // 로그인 성공 시 home 페이지로 이동
            return "home";
        } else {
            // 로그인 실패 시 다시 로그인 페이지로 리턴
            return "auth/signin";
        }
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
            return "auth/fail";
        }
        else { // if succeed
            return "auth/verified";

        }


    }

}
