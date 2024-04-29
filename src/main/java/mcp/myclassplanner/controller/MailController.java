package mcp.myclassplanner.controller;


import jakarta.mail.internet.MimeMessage;
import mcp.myclassplanner.model.dto.MailTO;
import mcp.myclassplanner.model.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService){

        this.mailService = mailService;
    }
    @GetMapping("/send")
    public MailTO sendTestMail(String email) {

        MailTO mailTO = new MailTO();

        mailTO.setAddress(email);
        mailTO.setTitle("My Class Planner confirmation email");
        mailTO.setMessage(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://localhost:9080/member/signUpConfirm?email=")
                .append(email)
                .append("&authKey=")
                .append("1234")
                .append("' target='_blenk'>이메일 인증 확인</a>")
                .toString());

        mailService.sendMail(mailTO);

        return mailTO;
    }

}
