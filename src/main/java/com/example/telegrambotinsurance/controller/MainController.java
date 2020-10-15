package com.example.telegrambotinsurance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

// Класс главный контроллер, отвечает за обработку запросов на главную страницу ("/").
public class MainController {

    // Обрабатывать запросы, которые приходят на адрес "/" (на главную страницу).
    // Возвращает название html-ки, которую спринг покажет пользователю в ответ.
    @RequestMapping("/")
    public String htmlWelcome() {
        return "welcome.html";
    }
}
