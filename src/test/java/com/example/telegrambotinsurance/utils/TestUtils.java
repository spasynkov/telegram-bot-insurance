package com.example.telegrambotinsurance.utils;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

public class TestUtils {
	public static void checkTelegramResponseAfterSendingMessage(JSONObject response){
		//Проверка, что ответ не пустой
		Assertions.assertNotNull(response);
		//Проверка, что ответ удачный
		Assertions.assertEquals(response.optString("ok"), "true", "Wrong response from telegram servers, field: ok = false");
	}
}
