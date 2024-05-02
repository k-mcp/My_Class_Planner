package mcp.myclassplanner.model.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import mcp.myclassplanner.model.dto.MailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    @Autowired
    public MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendMail(MailTO mail){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(mail.getAddress());
            helper.setSubject(mail.getTitle());
            helper.setText(mail.getMessage(), true); // 두 번째 인자인 true는 HTML 형식임을 나타냅니다.
            System.out.println("mail.getAddress() = " + mail.getAddress());
        } catch (MessagingException e) {
            throw new RuntimeException("메일 전송 실패", e);
        }
        System.out.println("message = " + message);
        mailSender.send(message);
    }
}
