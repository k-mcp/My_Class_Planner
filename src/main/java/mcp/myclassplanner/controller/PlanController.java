package mcp.myclassplanner.controller;

//import mcp.myclassplanner.model.service.PlanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlanController {

//    private final PlanService planService;

//    public PlanController(PlanService planService){
//        this.planService = planService;
//    }

    @GetMapping("/plan")
    public String plan(HttpSession httpSession){
        // 세션에 사용자 정보 저장
        String username = (String) httpSession.getAttribute("loginid");
        if (username != null) {
            return "redirect:/home";
        } else {
            return "redirect:/auth/signin";
        }

    }
    @GetMapping("/generate")
    public String generate(HttpSession httpSession){
        // 세션에 사용자 정보 저장
        String username = (String) httpSession.getAttribute("loginid");
        if (username != null) {
            return "redirect:/home";
        } else {
            return "redirect:/auth/signin";
        }

    }
    @PostMapping("/generate")
    public String generateForm(){
        return "/plan/generate";
    }
}
