package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mcp.myclassplanner.model.dto.MemberDTO;
import mcp.myclassplanner.model.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/signin2")
    public ModelAndView signIn(String message, ModelAndView mv){
        message = message.replace('-', ' ');
        mv.addObject("message", message);
        mv.setViewName("auth/signin");
        return mv;
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
                    mv.addObject("message", message);
                    mv.setViewName("redirect:/mail/send?email="+ memberService.getEmail(username));
                    return mv;
                }
                // 세션에 사용자 정보 저장
                httpSession.setAttribute("username",username);
                httpSession.setAttribute("memberCode", memberService.getMemberCodeByEmail(memberService.getEmail(username)));
                mv.setViewName("redirect:/home");
                return mv;


        }
        mv.addObject("message", message);
        mv.setViewName("auth/signin");
        return mv;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        if (httpSession != null) {
            httpSession.removeAttribute("username");
            httpSession.removeAttribute("memberCode");
            httpSession.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup(){
        return "auth/signup";
    }
    @PostMapping("/signup")
    public ModelAndView signupPro(MemberDTO memberDTO, ModelAndView mv){

        int result = memberService.signUp(memberDTO);
        if(result == 1) mv.setViewName("redirect:/mail/send?email=" + memberDTO.getEmail());
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

    @GetMapping(value="getAllMembers", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String memberExist() throws JsonProcessingException {
        List<MemberDTO> memberDTOList = memberService.getAllMembers();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(memberDTOList);
    }
    @GetMapping("/guestSignin")
    public String guestSignin(HttpSession httpSession){

        MemberDTO member = new MemberDTO();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 8; i++){
            sb.append((int)(Math.random()*100));
        }
        member.setUsername("Guest" + sb);
        member.setPassword(member.getUsername());
        member.setEmail(member.getUsername()+ "@gmail.com");
        member.setAuthCode("1111");
        member.setAuthStatus(1);
        int result = memberService.signInAsGuest(member);
        if(result == 1){
            int memberCode = memberService.getMemberCodeByEmail(member.getEmail());
            member.setMemberCode(memberCode);
            httpSession.setAttribute("memberCode", memberCode);
            httpSession.setAttribute("username", member.getUsername().substring(0,5));
            return "redirect:/home";
        }
//        httpSession.setAttribute();
        return "redirect:/";
    }
}
