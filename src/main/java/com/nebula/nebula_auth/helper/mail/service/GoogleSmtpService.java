package com.nebula.nebula_auth.helper.mail.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Random;

@Service
public class GoogleSmtpService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String subject, String content, List<String> receivers){
        Random random = new Random();
        int certCode = random.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + certCode);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("official.52hertz@gmail.com");
            helper.setSubject(subject);
            helper.setText("text/html", content);
            for (String to : receivers){
                helper.setTo(to);
                javaMailSender.send(message);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
