package mcp.myclassplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactusController {

    private JavaMailSender mailSender;

    @Autowired
    public ContactusController(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @GetMapping("/contactUs")
    public String contactUs(){
        return "/contactUs/contactUs";
    }

    @PostMapping("/contactUs")
    public String sendEmail(@RequestParam("name") String name,
                            @RequestParam("email") String email,
                            @RequestParam("message") String message){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("myclassplanner01@gmail.com");
        mailMessage.setSubject("NEw message from Contact Us page");
        mailMessage.setText("name : " + name + "\nemail : " + email + "\nmessage : " + message);

        mailSender.send(mailMessage);

        return "home";
    }
}
