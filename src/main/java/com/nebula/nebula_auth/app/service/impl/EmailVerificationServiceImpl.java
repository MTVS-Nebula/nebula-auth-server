package com.nebula.nebula_auth.app.service.impl;

import com.nebula.nebula_auth.app.dao.entity.EmailVerificationCode;
import com.nebula.nebula_auth.app.dao.repository.EmailVerificationCodeRepository;
import com.nebula.nebula_auth.app.service.EmailVerificationService;
import com.nebula.nebula_auth.helper.mail.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {
    private final SendEmailService sendEmailService;
    private final EmailVerificationCodeRepository emailVerificationCodeRepository;

    @Autowired
    public EmailVerificationServiceImpl(SendEmailService sendEmailService,
                                        EmailVerificationCodeRepository emailVerificationCodeRepository) {
        this.sendEmailService = sendEmailService;
        this.emailVerificationCodeRepository = emailVerificationCodeRepository;
    }

    @Override
    @Transactional
    public void sendVerificationEmail(String email) {
        Random random = new Random();
        int verificationCode = random.nextInt(888888) + 111111;
        emailVerificationCodeRepository.deleteByEmail(email);
        EmailVerificationCode emailVerificationCode = new EmailVerificationCode(email, verificationCode);
        emailVerificationCodeRepository.save(emailVerificationCode);
        String content = "code : " + verificationCode;
        List<String> receivers = List.of(email);
        sendEmailService.send("[52Hertz] 인증코드",content,receivers);
    }
}
