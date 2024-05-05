package mcp.myclassplanner.controller;

//import mcp.myclassplanner.model.service.PlanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.dto.CourseDTO;
import mcp.myclassplanner.model.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
public class PlanController {

    private final CourseService courseService;

    @Autowired
    public PlanController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/generate")
    public ModelAndView plan(HttpSession session, ModelAndView mv){
        // 세션에 사용자 정보 저장
        int memberCode = (int) session.getAttribute("memberCode");
        List<CourseDTO> courseDTOList = courseService.viewAllCourse(memberCode);

        // mv 추가
        mv.addObject("courseDTOList",courseDTOList);

        mv.setViewName("plan/generate");
        return mv;

    }
    @PostMapping("/generate")
    public String generateForm(HttpSession session, ModelAndView mv, HttpServletRequest request){
        Map<String, String[]> parameters = request.getParameterMap();
        int memberCode = (int) session.getAttribute("memberCode");
        List<CourseDTO> courseDTOList = courseService.viewAllCourse(memberCode);
        mv.addObject("courseDTOList", courseDTOList);
        return "/plan/generate";
    }
}
