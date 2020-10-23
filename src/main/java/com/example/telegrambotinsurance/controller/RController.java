package com.example.telegrambotinsurance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**

 * Класс ливит запросы на корневой запрос по адресу "/rest"

 */


@RestController
@RequestMapping("/rest")
public class RController {

    public RController() {
    }

    // Метод ливит запросы на корневой запрос по адресу "/rest".
    @RequestMapping()
    public Message getMessage() {
        return new Message();
    }

    private class Message {
        private String message = "Hello";

        public Message() {
        }

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
