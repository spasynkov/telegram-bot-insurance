package com.example.telegrambotinsurance.modelbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Телеграмм бот страхования
 */
@Component
public class BotInsurance extends AbstractBot{

    //@Value присваивает переменной token начение из конфигурации
    public BotInsurance(@Value("${bot-insurance.token}") String token) {
        super(token);
    }
}
