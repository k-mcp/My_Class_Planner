package mcp.myclassplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlanController {
    @GetMapping("/plan")
    public String plan(){
        return "/plan/plan";
    }
    @GetMapping("/generate")
    public String generate(){
        return "/plan/generate";
    }
    @PostMapping("/generate")
    public String generateForm(){
        return "/plan/generate";
    }
}
