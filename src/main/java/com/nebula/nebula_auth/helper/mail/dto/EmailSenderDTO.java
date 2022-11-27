package com.nebula.nebula_auth.helper.mail.dto;

import com.amazonaws.services.simpleemail.model.*;

import java.util.List;

public class EmailSenderDTO {
    public static final String FROM_EMAIL = "admin@mtvs-nebula.com"; // 보내는 사람

    private final List<String> to; // 받는 사람
    private final String subject; // 제목
    private final String content; // 본문

    public EmailSenderDTO(final List<String> to, final String subject,
                          final String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public SendEmailRequest toSendRequestDto() {
        final Destination destination = new Destination()
                .withToAddresses(this.to);

        final Message message = new Message()
                .withSubject(createContent(this.subject))
                .withBody(new Body()
                        .withHtml(createContent(this.content)));

        return new SendEmailRequest()
                .withSource(FROM_EMAIL)
                .withDestination(destination)
                .withMessage(message);
    }

    private Content createContent(final String text) {
        return new Content()
                .withCharset("UTF-8")
                .withData(text);
    }
}
