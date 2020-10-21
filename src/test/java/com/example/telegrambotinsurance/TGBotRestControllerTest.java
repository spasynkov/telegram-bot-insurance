package com.example.telegrambotinsurance;

import com.example.telegrambotinsurance.controller.TGBotRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TGBotRestControllerTest {

    @Test
    public void newReturnRestShouldBeMessageHello() {
        //action
        TGBotRestController tgBotRestController = new TGBotRestController();
        String message = tgBotRestController.rest().getMessage();
        String expectAnswer = "Hello";

        //assertion
        Assertions.assertEquals(message, expectAnswer);

    }
}
