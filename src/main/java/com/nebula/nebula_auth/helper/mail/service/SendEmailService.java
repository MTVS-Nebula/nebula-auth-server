package com.nebula.nebula_auth.helper.mail.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.nebula.nebula_auth.helper.mail.dto.EmailSenderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendEmailService {
    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SendEmailService(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    public void send(final String subject, final String content, final List<String> receivers) {
        final EmailSenderDTO senderDto = new EmailSenderDTO(receivers,subject,content);

        final SendEmailResult sendEmailResult = amazonSimpleEmailService // 2
                .sendEmail(senderDto.toSendRequestDto());

        sendingResultMustSuccess(sendEmailResult); // 3
    }

    private void sendingResultMustSuccess(final SendEmailResult sendEmailResult) {
        if (sendEmailResult.getSdkHttpMetadata().getHttpStatusCode() != 200) {
            System.out.println("[ERROR] " + sendEmailResult.getSdkResponseMetadata().toString());
        }
    }
}
