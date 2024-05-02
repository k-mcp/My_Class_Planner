package mcp.myclassplanner.controller;


import jakarta.mail.internet.MimeMessage;
import mcp.myclassplanner.model.dto.MailTO;
import mcp.myclassplanner.model.service.MailService;
import mcp.myclassplanner.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;
    private final MemberService memberService;
    @Autowired
    public MailController(MailService mailService, MemberService memberService){

        this.mailService = mailService;
        this.memberService = memberService;
    }
    @GetMapping("/send")
    public ModelAndView sendTestMail(String email, ModelAndView mv) {

        String key = memberService.getAuthCode(email);
        System.out.println("key = " + key);
        MailTO mailTO = new MailTO();

        mailTO.setAddress(email);
        mailTO.setTitle("My Class Planner confirmation email");
        mailTO.setMessage(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://localhost:8080/auth/signUpConfirm?email=")
                .append(email)
                .append("&authKey=")
                .append(key)
                .append("' target='_blenk'>이메일 인증 확인</a>")
                .toString());

        mailService.sendMail(mailTO);
        mv.setViewName("redirect:/loading");
        return mv;
    }

}
