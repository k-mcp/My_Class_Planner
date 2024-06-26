package mcp.myclassplanner.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;


@Controller
@PropertySource("classpath:application.properties")
public class ContactusController {

    private JavaMailSender mailSender;

    @Value("${API_KEY}")
    private String API_KEY;

    @Autowired
    public ContactusController(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @GetMapping("/contactUs")
    public String contactUs(Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("API_KEY", API_KEY);
        return "contactus/contactUs";
    }

    @PostMapping("/contactUs")
    public String sendEmail(@RequestParam("name") String name,
                                  @RequestParam("email") String email,
                                  @RequestParam("message") String message,
                                  Model model, HttpSession session){

        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("myclassplanner01@gmail.com");
        mailMessage.setSubject("NEw message from Contact Us page");
        mailMessage.setText("name : " + name + "\nemail : " + email + "\nmessage : " + message);


        try {
            mailSender.send(mailMessage);
            model.addAttribute("message", "Your message has been sent! ");   // 이메일 전송 성공
        } catch (MailException e) {
            model.addAttribute("message", "Oops! Something went wrong. Try again ! ");
            e.printStackTrace();    // 에러 내용 콘솔에 출력
        }
        model.addAttribute("API_KEY", API_KEY);

        return "contactus/contactUs";

    }
}
