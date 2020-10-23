package com.example.telegrambotinsurance.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RController {

    public RController() {
    }

    @RequestMapping("/rest")
    public Message getMessage() {
        return new Message();
    }

    private class Message {
        private String userName = "Вася";
        private int userAge = 30;
        private List<String> userMessages;

        public Message() {
            userMessages = new ArrayList<>();
            userMessages.add("Привет!");
            userMessages.add("Это мы Боты!");
            userMessages.add("Тебе конец кожаный мешок!");
        }

        public Message(String userName, int age, List<String> messages) {
            this.userName = userName;
            this.userAge = age;
            this.userMessages = messages;
        }

        public String getName() {
            return userName;
        }

        public void setName(String name) {
            this.userName = name;
        }

        public int getAge() {
            return userAge;
        }

        public void setAge(int age) {
            this.userAge = age;
        }

        public List<String> getMessages() {
            return userMessages;
        }

        public void setMessages(List<String> messages) {
            this.userMessages = messages;
        }
    }
}
