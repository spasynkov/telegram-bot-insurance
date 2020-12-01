package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.keyboards.InlineKeyboardButton;
import com.example.telegrambotinsurance.keyboards.ReplyKeyboardButton;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KeyboardsServiceTest {
	//Здесь Spring вносит значения в нужное поля
	@Autowired
	SendRequestsService sendRequestsService;

	@Autowired
	KeyboardsService keyboardService;

	//Здесь Spring вносит значения в нужное поля
	@Value("${bot.insurance.token}") String token;

	@Test
	void getStarterMenu() {
		keyboardService.createReplyKeyboard(true,true,null);
		keyboardService.addRow();
		keyboardService.addRow();
		keyboardService.addButton(0,0,new ReplyKeyboardButton("Оформить договор"));
		keyboardService.addButton(new ReplyKeyboardButton("Контакты"));
		keyboardService.addButton(1,new ReplyKeyboardButton("Оплата"));
		keyboardService.addButton(new ReplyKeyboardButton("Помощь"));
		JSONObject JSONKeyboard = keyboardService.getKeyboard();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JSONKeyboard.length() != 0);

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-386295340, \"text\":\"Starter menu\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
		//Проверка, что ответ не пустой
		Assertions.assertNotNull(response);
		//Проверка, что ответ удачный
		Assertions.assertEquals(response.optString("ok"), "true", "Wrong response from telegram servers, field: ok = false");
	}

	@Test
	void removeKeyboard(){
		JSONObject JSONKeyboard = keyboardService.removeKeyboard();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JSONKeyboard.length() != 0);

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-386295340, \"text\":\"Delete keyboard\", \"reply_markup\":" + JSONKeyboard.toString() + "}");
		//Проверка, что ответ не пустой
		Assertions.assertNotNull(response);
		//Проверка, что ответ удачный
		Assertions.assertEquals(response.optString("ok"), "true", "Wrong response from telegram servers, field: ok = false");
	}



	@Test
	void getTestInlineKeyboard(){
		keyboardService.createInlineKeyboard();
		keyboardService.addRow();
		keyboardService.addRow();
		keyboardService.addButton(0, InlineKeyboardButton.builder()
				.text("1").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build());
		keyboardService.addButton(1, InlineKeyboardButton.builder()
				.text("2").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build());
		JSONObject JSONKeyboard = keyboardService.getKeyboard();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JSONKeyboard.length() != 0);

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-386295340, \"text\":\"Inline keyboard\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
		//Проверка, что ответ не пустой
		Assertions.assertNotNull(response);
		//Проверка, что ответ удачный
		Assertions.assertEquals(response.optString("ok"), "true", "Wrong response from telegram servers, field: ok = false");
	}
}