package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import mcp.myclassplanner.model.service.MemberService;

@Controller
@PropertySource("classpath:application.properties")
public class HomeController {
    @Value("${API_KEY}")
    private String API_KEY;


    @GetMapping("/")
    public String welcome(){
        return "home/welcome";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        session.getAttributeNames();
//        session.setAttribute("memberCode");
        model.addAttribute("username",session.getAttribute("username"));
        model.addAttribute("memberCode", session.getAttribute("memberCode"));
        model.addAttribute("API_KEY", API_KEY);
        return "home/home";
    }
    @GetMapping("/loading")
    public String loading() {
        return "common/loading";
    }

    @GetMapping("/openai")
    public String openai(){
        return "openai/chatRoom";
    }

}
