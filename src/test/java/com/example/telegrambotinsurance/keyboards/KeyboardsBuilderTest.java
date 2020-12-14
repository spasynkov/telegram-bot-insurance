package com.example.telegrambotinsurance.keyboards;

import com.example.telegrambotinsurance.keyboards.*;
import com.example.telegrambotinsurance.service.SendRequestsService;
import com.example.telegrambotinsurance.utils.TestUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KeyboardsBuilderTest {
	//Здесь Spring вносит значение в нужное поля
	@Autowired
	SendRequestsService sendRequestsService;

	//Здесь Spring вносит значение в нужное поля
	@Value("${bot.insurance.token}") String token;

	@Test
	void getStarterMenu() {
		ReplyKeyboardMarkup keyboard = ReplyKeyboardMarkup.builder()
				.setOne_time_keyboard(true)
				.setResize_keyboard(true)
				.addRow()
				.addRow()
				.addButton(0,0,new ReplyKeyboardButton("Оформить договор"))
				.addButton(new ReplyKeyboardButton("Контакты"))
				.addButton(1,new ReplyKeyboardButton("Оплата"))
				.addButton(ReplyKeyboardButton.builder().text("Помощь").build())
				.build();

		JSONObject JSONKeyboard = keyboard.toJson();

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-386295340, \"text\":\"Starter menu\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}

	@Test
	void removeKeyboard() {
		ReplyKeyboardRemove keyboard = ReplyKeyboardRemove.builder().selective(false).build();

		JSONObject JSONKeyboard = keyboard.toJson();

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-386295340, \"text\":\"Delete keyboard\", \"reply_markup\":" + JSONKeyboard.toString() + "}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}

	@Test
	void getTestInlineKeyboard() {
		InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addButton(0, InlineKeyboardButton.builder()
						.text("1").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build())
				.addButton(1, InlineKeyboardButton.builder()
						.text("2").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").pay(true).build())
				.build();

		JSONObject JSONKeyboard = keyboard.toJson();

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-386295340, \"text\":\"Inline keyboard\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}

	@Test
	void checkingBuildsViaIndexes() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addButton(0,0,new ReplyKeyboardButton("Оформить договор"))
				.addButton(new ReplyKeyboardButton("Контакты"))
				.addButton(1,new ReplyKeyboardButton("Оплата"))
				.addButton(new ReplyKeyboardButton("Помощь"))
				.build();
		JSONObject JSONReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JSONReplyKeyboardMarkup.length() != 0,"ReplyKeyboardMarkup is empty");

		InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addButton(0, InlineKeyboardButton.builder()
						.text("1").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build())
				.addButton(1,0, InlineKeyboardButton.builder()
						.text("2").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build())
				.build();

		JSONObject JSONInlineKeyboardMarkup = inlineKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JSONInlineKeyboardMarkup.length() != 0,"InlineKeyboardMarkup is empty");

		ReplyKeyboardRemove replyKeyboardRemove = ReplyKeyboardRemove.builder().selective(false).build();

		JSONObject JSONReplyKeyboardRemove = replyKeyboardRemove.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JSONReplyKeyboardRemove.length() != 0,"ReplyKeyboardRemove is empty");
	}
}