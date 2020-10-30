package com.example.telegrambotinsurance.configs;

import com.example.telegrambotinsurance.modelbot.AbstractBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class BotConfigs {

//    Создание Map с ключами в виде токенов и значениями в виде самих ботов
    @Bean("BotsWithTokens")
    @Autowired
    public Map<String, AbstractBot> createBotsMapWithTokens(List<AbstractBot> bots){
        Map<String,AbstractBot> sortedBots = new HashMap<>();
        for (AbstractBot bot:
             bots) {
            sortedBots.put(bot.getToken(),bot);
        }
        return sortedBots;
    }

}
