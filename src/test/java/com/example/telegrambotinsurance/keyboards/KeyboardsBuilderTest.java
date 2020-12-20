package com.example.telegrambotinsurance.keyboards;

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
	//@ToDo поменять на TelegramApi
	@Autowired
	SendRequestsService sendRequestsService;

	//Здесь Spring вносит значение в нужное поля
	@Value("${bot.insurance.token}") String token;
	private final String idChat = "-386295340";

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
				"{\"chat_id\":" + idChat + ", \"text\":\"Starter menu\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}

	@Test
	void removeKeyboard() {
		ReplyKeyboardRemove keyboard = ReplyKeyboardRemove.builder().selective(false).build();

		JSONObject JSONKeyboard = keyboard.toJson();

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":" + idChat + ", \"text\":\"Delete keyboard\", \"reply_markup\":" + JSONKeyboard.toString() + "}");
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
						.text("2").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build())
				.build();

		JSONObject JSONKeyboard = keyboard.toJson();

		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":" + idChat + ", \"text\":\"Inline keyboard\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}

	@Test
	void checkingBuildsViaIndexes() {

	}

	@Test
	void checkingBuildsViaIndexesForReply() {
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
		Assertions.assertTrue(JsonLengthNotZero(JSONReplyKeyboardMarkup),"ReplyKeyboardMarkup is empty");
	}

	private boolean JsonLengthNotZero(JSONObject JSONReplyKeyboardMarkup) {
		return JSONReplyKeyboardMarkup.length() != 0;
	}

	@Test
	void checkingBuildsViaIndexesForInline() {
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
		Assertions.assertTrue(JsonLengthNotZero(JSONInlineKeyboardMarkup),"InlineKeyboardMarkup is empty");
	}

	@Test
	void checkingBuildsViaIndexesForRemove() {
		ReplyKeyboardRemove replyKeyboardRemove = ReplyKeyboardRemove.builder().selective(false).build();

		JSONObject JSONReplyKeyboardRemove = replyKeyboardRemove.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JsonLengthNotZero(JSONReplyKeyboardRemove),"ReplyKeyboardRemove is empty");
	}

	@Test
	void addButtonWithOneParameter() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addButton(new ReplyKeyboardButton("Проверка метода с одним параметром"))
				.build();

		JSONObject JSONReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JsonLengthNotZero(JSONReplyKeyboardMarkup),"ReplyKeyboardMarkup is empty");
	}

	@Test
	void addButtonWithTwoParameter() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addButton(0,new ReplyKeyboardButton("Проверка метода с двумя параметром 1"))
				.addButton(0,new ReplyKeyboardButton("Проверка метода с двумя параметром 2"))
				.build();

		JSONObject JSONReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JsonLengthNotZero(JSONReplyKeyboardMarkup),"ReplyKeyboardMarkup is empty");
	}

	@Test
	void addButtonWithThreeParameter() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addButton(1,0,new ReplyKeyboardButton("Проверка метода с тремя параметром 1"))
				.addButton(0,0,new ReplyKeyboardButton("Проверка метода с тремя параметром 2"))
				.build();

		JSONObject JSONReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JsonLengthNotZero(JSONReplyKeyboardMarkup),"ReplyKeyboardMarkup is empty");
	}

	@Test
	void addRow() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addRow()
				.addButton(1,0,new ReplyKeyboardButton("Проверка метода addRow()"))
				.build();

		JSONObject JSONReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(JsonLengthNotZero(JSONReplyKeyboardMarkup),"ReplyKeyboardMarkup is empty");
	}
}