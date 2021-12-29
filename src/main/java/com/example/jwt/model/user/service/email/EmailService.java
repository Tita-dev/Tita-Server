package com.example.jwt.model.user.service.email;

public interface EmailService {
    void sendMail(String to, String sub, String text);
}
