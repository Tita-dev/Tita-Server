package com.example.jwt.model.user.service.email;

import com.amazonaws.services.simpleemail.model.SendEmailResult;

public interface EmailService {
    void send(final String receivers,final String subject, final String content);
    void sendingResultMustSuccess(final SendEmailResult sendEmailResult);
}
