package mcp.myclassplanner.controller;

//import mcp.myclassplanner.model.service.PlanService;
import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.dto.CourseDTO;
import mcp.myclassplanner.model.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PlanController {

    private final CourseService courseService;

    @Autowired
    public PlanController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/generate")
    public String plan(HttpSession session){
        // 세션에 사용자 정보 저장
        int memberCode = (int) session.getAttribute("memberCode");
        List<CourseDTO> courseDTO = courseService.viewAllCourse(memberCode);
        // mv 추가
        return "plan/plan";

    }
    @PostMapping("/generate")
    public String generateForm(){
        return "/plan/generate";
    }
}
