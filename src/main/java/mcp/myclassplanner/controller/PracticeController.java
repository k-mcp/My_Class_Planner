package mcp.myclassplanner.controller;

import mcp.myclassplanner.model.dto.PracticeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PracticeController {
    @GetMapping("/practice")
    public String practice(){
        return "practice";
    }
    @PostMapping("/practice")
    public String practicePro(PracticeDTO practice){
        System.out.println("practice = " + practice);
        String p = practice.getStart();
        System.out.println("practice = " + p);
        return "practice";
    }
}
