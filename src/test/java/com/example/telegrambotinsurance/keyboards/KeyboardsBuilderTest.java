package com.example.telegrambotinsurance.keyboards;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.telegrambotinsurance.service.SendRequestsService;
import com.example.telegrambotinsurance.utils.TestUtils;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class KeyboardsBuilderTest {
	private final String chatId = "-386295340";
	//Здесь Spring вносит значение в нужное поля
	@Autowired
	private SendRequestsService sendRequestsService;    // ToDo поменять на TelegramApi
	//Здесь Spring вносит значение в нужное поля
	@Value("${bot.insurance.token}")
	private String token;

	@Test
	@Order(1)
		// run before remove keyboard test
	void getStarterMenu() {
		ReplyKeyboardMarkup keyboard = ReplyKeyboardMarkup.builder()
				.setOneTimeKeyboard(true)
				.setResizeKeyboard(true)
				.addRow()
				.addRow()
				.addButton(0, 0, new ReplyKeyboardButton("Оформить договор"))
				.addButton(new ReplyKeyboardButton("Контакты"))
				.addButton(1, new ReplyKeyboardButton("Оплата"))
				.addButton(ReplyKeyboardButton.builder().text("Помощь").build())
				.build();

		JSONObject jsonKeyboard = keyboard.toJson();

		JSONObject response = sendRequestsService.sendPost(token, "sendMessage",
				"{\"chat_id\":" + chatId + ", \"text\":\"Starter menu\", \"reply_markup\":" + jsonKeyboard.toString() + "}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}

	@Test
	@Order(2)
		// run after reply keyboard test
	void removeKeyboard() {
		ReplyKeyboardRemove keyboard = ReplyKeyboardRemove.builder().build();

		JSONObject JSONKeyboard = keyboard.toJson();

		JSONObject response = sendRequestsService.sendPost(token, "sendMessage",
				"{\"chat_id\":" + chatId + ", \"text\":\"Delete keyboard\", \"reply_markup\":" + JSONKeyboard.toString() + "}");
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

		JSONObject jsonKeyboard = keyboard.toJson();

		JSONObject response = sendRequestsService.sendPost(token, "sendMessage",
				"{\"chat_id\":" + chatId + ", \"text\":\"Inline keyboard\", \"reply_markup\":" + jsonKeyboard.toString() + "}");
		TestUtils.checkTelegramResponseAfterSendingMessage(response);
	}

	@Test
	void checkingBuildsViaIndexesForReply() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addButton(0, 0, new ReplyKeyboardButton("Оформить договор"))
				.addButton(new ReplyKeyboardButton("Контакты"))
				.addButton(1, new ReplyKeyboardButton("Оплата"))
				.addButton(new ReplyKeyboardButton("Помощь"))
				.build();

		JSONObject jsonReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(jsonLengthNotZero(jsonReplyKeyboardMarkup), "ReplyKeyboardMarkup is empty");

		String expectedJson = "{\"keyboard\":[[{\"request_location\":false,\"text\":\"Оформить договор\",\"request_contact\":false}],[{\"request_location\":false,\"text\":\"Контакты\",\"request_contact\":false},{\"request_location\":false,\"text\":\"Оплата\",\"request_contact\":false},{\"request_location\":false,\"text\":\"Помощь\",\"request_contact\":false}]]}";
		Assertions.assertEquals(expectedJson, jsonReplyKeyboardMarkup.toString(), "Wrong json built: problem with json elements and/or ordering");
	}

	private boolean jsonLengthNotZero(JSONObject jsonReplyKeyboardMarkup) {
		return jsonReplyKeyboardMarkup.length() != 0;
	}

	@Test
	void checkingBuildsViaIndexesForInline() {
		InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addButton(0, InlineKeyboardButton.builder()
						.text("1").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build())
				.addButton(1, 0, InlineKeyboardButton.builder()
						.text("2").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build())
				.build();

		JSONObject jsonInlineKeyboardMarkup = inlineKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(jsonLengthNotZero(jsonInlineKeyboardMarkup), "InlineKeyboardMarkup is empty");

		String expectedJson = "{\"inline_keyboard\":[[],[{\"text\":\"2\",\"url\":\"https://core.telegram.org/bots/api#inlinekeyboardbutton\"},{\"text\":\"1\",\"url\":\"https://core.telegram.org/bots/api#inlinekeyboardbutton\"}]]}";
		Assertions.assertEquals(expectedJson, jsonInlineKeyboardMarkup.toString(), "Wrong json built: problem with json elements and/or ordering");
	}

	@Test
	void checkingBuildsViaIndexesForRemove() {
		ReplyKeyboardRemove replyKeyboardRemove = ReplyKeyboardRemove.builder().build();

		JSONObject jsonReplyKeyboardRemove = replyKeyboardRemove.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(jsonLengthNotZero(jsonReplyKeyboardRemove), "ReplyKeyboardRemove is empty");

		String expectedJson = "{\"remove_keyboard\":true}";
		Assertions.assertEquals(expectedJson, jsonReplyKeyboardRemove.toString(), "Wrong json built: problem with json elements and/or ordering");
	}

	@Test
	void addButtonWithOneParameter() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addButton(new ReplyKeyboardButton("Проверка метода с одним параметром"))
				.build();

		JSONObject jsonReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(jsonLengthNotZero(jsonReplyKeyboardMarkup), "ReplyKeyboardMarkup is empty");

		String expectedJson = "{\"keyboard\":[[{\"request_location\":false,\"text\":\"Проверка метода с одним параметром\",\"request_contact\":false}]]}";
		Assertions.assertEquals(expectedJson, jsonReplyKeyboardMarkup.toString(), "Wrong json built: problem with json elements and/or ordering");
	}

	@Test
	void addButtonWithTwoParameter() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addButton(0, new ReplyKeyboardButton("Проверка метода с двумя параметром 1"))
				.addButton(0, new ReplyKeyboardButton("Проверка метода с двумя параметром 2"))
				.build();

		JSONObject jsonReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(jsonLengthNotZero(jsonReplyKeyboardMarkup), "ReplyKeyboardMarkup is empty");

		String expectedJson = "{\"keyboard\":[[],[{\"request_location\":false,\"text\":\"Проверка метода с двумя параметром 2\",\"request_contact\":false},{\"request_location\":false,\"text\":\"Проверка метода с двумя параметром 1\",\"request_contact\":false}]]}";
		Assertions.assertEquals(expectedJson, jsonReplyKeyboardMarkup.toString(), "Wrong json built: problem with json elements and/or ordering");
	}

	@Test
	void addButtonWithThreeParameter() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addButton(1, 0, new ReplyKeyboardButton("Проверка метода с тремя параметром 1"))
				.addButton(0, 0, new ReplyKeyboardButton("Проверка метода с тремя параметром 2"))
				.build();

		JSONObject jsonReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(jsonLengthNotZero(jsonReplyKeyboardMarkup), "ReplyKeyboardMarkup is empty");

		String expectedJson = "{\"keyboard\":[[{\"request_location\":false,\"text\":\"Проверка метода с тремя параметром 2\",\"request_contact\":false}],[{\"request_location\":false,\"text\":\"Проверка метода с тремя параметром 1\",\"request_contact\":false}]]}";
		Assertions.assertEquals(expectedJson, jsonReplyKeyboardMarkup.toString(), "Wrong json built: problem with json elements and/or ordering");
	}

	@Test
	void addRow() {
		ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
				.addRow()
				.addRow()
				.addRow()
				.addButton(1, 0, new ReplyKeyboardButton("Проверка метода addRow()"))
				.build();

		JSONObject jsonReplyKeyboardMarkup = replyKeyboardMarkup.toJson();
		//Проверка, что JSONObject клавиатуры не пустой
		Assertions.assertTrue(jsonLengthNotZero(jsonReplyKeyboardMarkup), "ReplyKeyboardMarkup is empty");

		String expectedJson = "{\"keyboard\":[[],[{\"request_location\":false,\"text\":\"Проверка метода addRow()\",\"request_contact\":false}],[]]}";
		Assertions.assertEquals(expectedJson, jsonReplyKeyboardMarkup.toString(), "Wrong json built: problem with json elements and/or ordering");
	}
}