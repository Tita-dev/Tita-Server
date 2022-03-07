package com.example.jwt.model.user.service.email;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.example.jwt.model.user.dto.email.EmailSenderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceMpl implements EmailService {
    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public void send(final String receivers, final String subject, final String content) {
        final EmailSenderDto senderDto = EmailSenderDto.builder() // 1
                .to(receivers)
                .subject(subject)
                .content(content)
                .build();

        final SendEmailResult sendEmailResult = amazonSimpleEmailService // 2
                .sendEmail(senderDto.toSendRequestDto());

        sendingResultMustSuccess(sendEmailResult); // 3
    }

    public void sendingResultMustSuccess(final SendEmailResult sendEmailResult) {
        if (sendEmailResult.getSdkHttpMetadata().getHttpStatusCode() != 200) {
            log.error("{}", sendEmailResult.getSdkResponseMetadata().toString());
        }
    }
}
