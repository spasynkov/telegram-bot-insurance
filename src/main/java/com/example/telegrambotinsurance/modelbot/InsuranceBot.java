package com.example.telegrambotinsurance.modelbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Телеграмм бот страхования
 */
@Component
public class InsuranceBot extends AbstractBot{

    //@Value присваивает переменной token значение из конфигурации
    public InsuranceBot(@Value("${bot.insurance.token}") String token) {
        super(token);
    }

    @Override
    public void processMessage(Message message) {

    }
}
