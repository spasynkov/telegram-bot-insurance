package com.example.telegrambotinsurance.modelbot;


public class AbstractBot {
    //Уникальный и неизменяемый токен
    protected final String token;

    public String getToken() {
        return token;
    }

    public AbstractBot(String token) {
        this.token = token;
    }

}
