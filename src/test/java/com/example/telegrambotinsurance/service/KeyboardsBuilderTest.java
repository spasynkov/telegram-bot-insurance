package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.keyboards.*;
import com.example.telegrambotinsurance.utils.TestUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KeyboardsBuilderTest {
	//Здесь Spring вносит значения в нужное поля
	@Autowired
	SendRequestsService sendRequestsService;

	//Здесь Spring вносит значения в нужное поля
	@Value("${bot.insurance.token}") String token;

	@Test
	void getStarterMenu() {
		JSONObject JSONKeyboard = KeyboardsBuilder.builder().builderReplyKeyboard(ReplyKeyboardMarkup.builder()
				.resize_keyboard(true).one_time_keyboard(true).selective(true).build())
				.addRow()
				.addRow()
				.addButton(0,0,new ReplyKeyboardButton("Оформить договор"))
				.addButton(new ReplyKeyboardButton("Контакты"))
				.addButton(1,new ReplyKeyboardButton("Оплата"))
				.addButton(new ReplyKeyboardButton("Помощь"))
				.build();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JSONKeyboard.length() != 0);

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-386295340, \"text\":\"Starter menu\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}

	@Test
	void removeKeyboard(){
		JSONObject JSONKeyboard = KeyboardsBuilder.builder().buildRemoveKeyboard(ReplyKeyboardRemove.builder().selective(false).build());
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JSONKeyboard.length() != 0);

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-386295340, \"text\":\"Delete keyboard\", \"reply_markup\":" + JSONKeyboard.toString() + "}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}



	@Test
	void getTestInlineKeyboard(){
		JSONObject JSONKeyboard = KeyboardsBuilder.builder().builderInlineKeyboard(new InlineKeyboardMarkup())
				.addRow()
				.addRow()
				.addButton(0, InlineKeyboardButton.builder()
						.text("1").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build())
				.addButton(1, InlineKeyboardButton.builder()
						.text("2").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build())
				.build();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JSONKeyboard.length() != 0);

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-386295340, \"text\":\"Inline keyboard\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}
}