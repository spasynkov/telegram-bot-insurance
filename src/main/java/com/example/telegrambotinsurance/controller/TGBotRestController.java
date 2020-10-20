package com.example.telegrambotinsurance.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TGBotRestController {

    @RequestMapping("/rest")
    @ResponseBody
    public String rest() {
        return "Hello";
    }
}
