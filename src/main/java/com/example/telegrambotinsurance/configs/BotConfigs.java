package com.example.telegrambotinsurance.configs;

import com.example.telegrambotinsurance.modelbot.AbstractBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BotConfigs {

    //Создание Map с ключами в виде токенов и значениями в виде самих ботов
    @Bean("BotsWithTockens")
    @Autowired
    public Map<String, AbstractBot> createBotsMapWithTockens(Map<String,AbstractBot> bots){
        Map<String,AbstractBot> sortedBots = new HashMap<>();
        for (Map.Entry<String,AbstractBot> bot:
             bots.entrySet()) {
            sortedBots.put(bot.getValue().getToken(),bot.getValue());
        }
        return sortedBots;
    }

}
