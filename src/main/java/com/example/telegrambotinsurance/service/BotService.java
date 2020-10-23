package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.modelbot.AbstractBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BotService implements InterfaceService{
    Map<String, AbstractBot> bots;

    //Аннотация вносит в Map имя бота и бин
    @Autowired
    public BotService(Map<String, AbstractBot> bots) {
        this.bots = bots;
    }

    //Метод ищет бота по токену
    @Override
    public AbstractBot findBotByToken(String token) throws BotNotFoundException {
        for (Map.Entry<String, AbstractBot> bot:
             bots.entrySet()) {
            AbstractBot selectedBot = bot.getValue();
            if (token.equals(selectedBot.getToken())){
                return selectedBot;
            }
        }
        //Если бот не найден, то вылетает ошибка
        throw new BotNotFoundException();
    }
}
