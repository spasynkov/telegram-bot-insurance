package com.example.telegrambotinsurance;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.service.BotServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class ServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotServiceImpl.class);
    //Здесь Spring вносит значения в нужные поля
    //Аннотация @Qualifier нужна, так как без неё, Spring создает свою Map
    @Autowired
    @Qualifier("BotsWithTokens")
    Map<String, AbstractBot> bots;

    @Autowired
    BotServiceImpl botService;

    //В этом тесте сервис находит бота по токену и проверяет один ли это и тот же объект
    @Test
    void serviceTest(){
        try {
            String token = (String) bots.keySet().toArray()[0];
            AbstractBot bot = bots.get(token);
            AbstractBot bot1 = botService.findBotByToken(token);
            Assertions.assertSame(bot,bot1);
        } catch (BotNotFoundException e) {
            LOGGER.debug(e.toString());
            Assertions.fail();
        }

    }
}
