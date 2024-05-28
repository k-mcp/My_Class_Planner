package mcp.myclassplanner.controller;

//import mcp.myclassplanner.model.service.PlanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mcp.myclassplanner.model.dto.CourseDTO;
import mcp.myclassplanner.model.dto.PlanDTO;
import mcp.myclassplanner.model.dto.ScheduleDTO;
import mcp.myclassplanner.model.dto.SectionDTO;
import mcp.myclassplanner.model.service.CourseService;
import mcp.myclassplanner.model.service.MemberService;
import mcp.myclassplanner.model.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Member;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@PropertySource("classpath:application.properties")
public class PlanController {
    @Value("${API_KEY}")
    private String API_KEY;

    private final CourseService courseService;
    private final PlanService planService;
    private final MemberService memberService;
    @Autowired
    public PlanController(CourseService courseService, PlanService planService, MemberService memberService) {
        this.courseService = courseService;
        this.planService = planService;
        this.memberService = memberService;
    }

    @GetMapping("/generate")
    public String plan(HttpSession session, Model model){
        model.addAttribute("username",session.getAttribute("username"));
        // 세션에 사용자 정보 저장
        int memberCode = (int) session.getAttribute("memberCode");
        List<CourseDTO> courseDTOList = courseService.viewAllCourse(memberCode);

        // mv 추가
        model.addAttribute("courseDTOList",courseDTOList);
        model.addAttribute("API_KEY", API_KEY);
        return "plan/generate";

    }
    @PostMapping("/generate")
    public String generateForm(HttpSession session, ModelAndView mv, HttpServletRequest request){
        mv.addObject("username",session.getAttribute("username"));
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
        Map<String, Integer> exMap = new HashMap<>();
        exMap.put("memberCode", memberCode);
        exMap.put("exp", 3);
        memberService.addExp(exMap);
        return "redirect:/generatePro";
    }
    @GetMapping("generatePro")
    public String generatePro(HttpSession session, Model model, RedirectAttributes flash){
        List<ScheduleDTO> scheduleDTOS = planService.viewResult();
        if(scheduleDTOS.size() == 0){
            flash.addFlashAttribute("message", "There is no available Schedule.");
            return "redirect:/generate";
        }
        for(ScheduleDTO scheduleDTO : scheduleDTOS){
            if (scheduleDTO.getDays().contains("X")){
                scheduleDTO.setDays(scheduleDTO.getDays().replace("X","Th"));
            }
        }
        Map<Integer, List<ScheduleDTO>> groupedSchedules = scheduleDTOS.stream()
                .collect(Collectors.groupingBy(ScheduleDTO::getCaseNo));
        model.addAttribute("groupedSchedules", groupedSchedules);
        model.addAttribute("API_KEY", API_KEY);
        return "plan/generatepro";
    }
    @PostMapping("saveSchedules")
    public String saveSchedules(HttpSession session, HttpServletRequest request){
        Map<String, String[]> parameters = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String i = Arrays.toString(entry.getValue()).replace("[", "").replace("]", "");
            planService.addNewPlan(i);
        }
        int memberCode = (int) session.getAttribute("memberCode");
        Map<String, Integer> exMap = new HashMap<>();
        exMap.put("memberCode", memberCode);
        exMap.put("exp", 2);
        memberService.addExp(exMap);
        return "redirect:/myplan";
    }
    @GetMapping("myplan")
    public String myplan(Model mv, HttpSession session){
        mv.addAttribute("username",session.getAttribute("username"));
        int memberCode = (int) session.getAttribute("memberCode");
        List<PlanDTO> planDTOS = planService.viewMyPlan(memberCode);
        for(PlanDTO planDTO : planDTOS){
            if (planDTO.getDays().contains("X")){
                planDTO.setDays(planDTO.getDays().replace("X","Th"));
            }
        }
        Map<Integer, List<PlanDTO>> groupedPlans = planDTOS.stream()
                .collect(Collectors.groupingBy(PlanDTO::getCaseNo));
        mv.addAttribute("groupedPlans", groupedPlans);
        mv.addAttribute("API_KEY", API_KEY);
        return "plan/plan";
    }


    @PostMapping("deletePlans")
    public String deletePlans(HttpServletRequest request, HttpSession session){
        Map<String, String[]> parameters = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String i = Arrays.toString(entry.getValue()).replace("[", "").replace("]", "");
            planService.deletePlan(i);
        }
        return "redirect:/myplan";
    }
}
