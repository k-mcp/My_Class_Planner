package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ModelAndView signInCheck(String username, String password, ModelAndView mv, HttpSession httpSession) {
        String message = "";
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        int result = memberService.signIn(map); // 0 = success 1= username not exist 2= password incorrect
        switch (result) {
            case 1: // username does not exist
                message = "username does not exist";
                break;
            case 2: // password incorrect
                message = "password is incorrect";
                break;
            case 0: // success
                int check = memberService.checkAuthStatus(username);
                if (check == 0) { // when the email is not authorized
                    message = "You have to verified your email first. We've sent another verification link to your email now";
                    // email verification
                    mv.setViewName("redirect:/mail/send?email="+ memberService.getEmail(username));
                    return mv;
                }
                // 세션에 사용자 정보 저장
                httpSession.setAttribute("loginid",username);

                mv.setViewName("redirect:/home");
                return mv;
        }

        mv.addObject("message", message);
        mv.setViewName("/auth/signin");
        return mv;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        if (httpSession != null) {
            httpSession.removeAttribute("loginid");
            httpSession.invalidate();
        }
        return "redirect:/auth/signin";
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
