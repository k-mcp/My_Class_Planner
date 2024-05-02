package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    @GetMapping("/add")
    public String addCourse(HttpSession httpSession) {
        // 세션에 사용자 정보 저장
        String username = (String) httpSession.getAttribute("loginid");
        if (username != null) {
            return "redirect:/home";
        } else {
            return "redirect:/auth/signin";
        }
    }

    @GetMapping("/course")
    public String viewCourse(HttpSession httpSession) {
        // 세션에 사용자 정보 저장
        String username = (String) httpSession.getAttribute("loginid");
        if (username != null) {
            return "redirect:/home";
        } else {
            return "redirect:/auth/signin";
        }
    }
}
