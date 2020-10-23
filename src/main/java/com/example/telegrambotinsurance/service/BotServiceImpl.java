package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.modelbot.AbstractBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BotServiceImpl implements BotService {
    Map<String, AbstractBot> bots;

    //Аннотация вносит в Map имя бота и бин
    @Autowired()
    public BotServiceImpl(@Qualifier("BotsWithTockens") Map<String, AbstractBot> bots) {
        this.bots = bots;
    }

    //Метод ищет бота по токену
    @Override
    public AbstractBot findBotByToken(String token) throws BotNotFoundException {
        if (!bots.containsKey(token)){
            //Если бот не найден, то вылетает ошибка
            throw new BotNotFoundException();
        }
        return bots.get(token);
    }
}
