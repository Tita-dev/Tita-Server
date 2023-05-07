package com.example.jwt.config.security.redis;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class KeyConfig {
    protected int size;

    public String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }

    //인증코드 난수 발생
    private String getAuthCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while (buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

}
