package com.example.telegrambotinsurance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Главный контроллер
 */
@Controller
public class MainController {
    /**
     *  Метод возращает название страницы  приветствия
     */
    @RequestMapping("/")
    public String getHtml(){
        return "WelcomePage.html";
    }
}
