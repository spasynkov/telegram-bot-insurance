package com.example.telegrambotinsurance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstRestController {
    //Метод ловит запросы на страницу /rest и отправляет json объект
    @RequestMapping("/rest")
    public MessageReply getMessage(){
        return new MessageReply("Hello");
    }

    //Создание вспомагательного класса для облегчения ответа
    //(Можно использовать место этого класса Map, но получится метод более больше
    class MessageReply{
        //Имя пременной будет отображенно в ключе json
        private final String message;

        public String getMessage() {
            return message;
        }

        public MessageReply(String message) {
            this.message = message;
        }
    }
}
