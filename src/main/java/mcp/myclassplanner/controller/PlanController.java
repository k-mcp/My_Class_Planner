package mcp.myclassplanner.controller;

//import mcp.myclassplanner.model.service.PlanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.dto.CourseDTO;
import mcp.myclassplanner.model.dto.SectionDTO;
import mcp.myclassplanner.model.service.CourseService;
import mcp.myclassplanner.model.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
public class PlanController {

    private final CourseService courseService;
    private final PlanService planService;

    @Autowired
    public PlanController(CourseService courseService, PlanService planService) {
        this.courseService = courseService;
        this.planService = planService;
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
        List<CourseDTO> courses = new ArrayList<>();
        CourseDTO courseDTO;
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String courseName = entry.getKey();
            String[] times = entry.getValue();
            courseDTO = new CourseDTO();
            courseDTO.setCourseName(courseName);
            List<SectionDTO> sectionDTOList = new ArrayList<>();
            for(String str : times){
                SectionDTO sectionDTO = new SectionDTO();
                String[] data = str.split("-");
                sectionDTO.setStartTime(Integer.parseInt(data[0]));
                sectionDTO.setEndTime(Integer.parseInt(data[1]));
                sectionDTO.setDays(data[2]);
                sectionDTOList.add(sectionDTO);
            }
            courseDTO.setSectionDTOList(sectionDTOList);
            courses.add(courseDTO);
        }
        int memberCode = (int) session.getAttribute("memberCode");
        planService.generatePro(courses,memberCode);
        return "redirect:/generatePro";
    }
    @GetMapping("generatePro")
    public String generatePro(HttpServletRequest request){

        return "plan/genereatePro";
    }
}
