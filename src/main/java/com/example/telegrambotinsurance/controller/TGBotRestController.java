package com.example.telegrambotinsurance.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TGBotRestController {

    @RequestMapping("/rest")
    public Cat rest() {
        return new Cat();
    }

     public class Cat {
        private String message = "Hello";

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
