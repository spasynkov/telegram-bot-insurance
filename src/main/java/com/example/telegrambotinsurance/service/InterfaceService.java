package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.modelbot.AbstractBot;

public interface InterfaceService {
    //Метод ищет бота по токену
    AbstractBot findBotByToken(String token) throws BotNotFoundException;
}
