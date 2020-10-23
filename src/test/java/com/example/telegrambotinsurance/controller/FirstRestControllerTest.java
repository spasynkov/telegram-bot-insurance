package com.example.telegrambotinsurance.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FirstRestControllerTest {

    @Test
    void getMessage() {
        FirstRestController firstRestController = new FirstRestController();
        Assertions.assertNotNull("Hello",firstRestController.getMessage().getMessage());
    }
}