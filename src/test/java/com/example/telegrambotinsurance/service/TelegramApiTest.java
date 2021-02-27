package com.example.telegrambotinsurance.service;


import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.OutgoingMessage;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TelegramApiTest {

	/**
	 * fields and class instances required for testing sending
	 * and receiving a response from the telegram API using spring boot
	 */
	@Autowired
	TelegramApi telegramApi;

	private final AbstractBot bot;

	/**
	 * constructor to obtain an instance of the bot
	 * @param token token of the bot
	 * @param service instance to obtain an instance of the bot
	 */
	@Autowired
	public TelegramApiTest(@Value("${bot.insurance.token}") String token, BotService service) {
		this.bot = service.findBotByToken(token);
	}

	/**
	 * testing the "getMe" request
	 */
	@Test
	public void methodGetMeShouldReturnedJSONAnswer() {

		JSONObject response = telegramApi.getMe(bot, bot.getToken());

		// response must be not null
		Assertions.assertNotNull(response);

		// response must have "ok" status
		Assertions.assertEquals("true", response.optString("ok"),
				"Wrong response from TG API, field: ok = false ");
	}

	/**
	 * testing the "getChat" request with long chat ID
	 */
	@Test
	public void integerMethodGetChatShouldReturnedJSONAnswer() {

		long chatId = 871614990;

		JSONObject response = telegramApi.getChat(bot, bot.getToken(), chatId);

		// response must be not null
		Assertions.assertNotNull(response);

		// response must have "ok" status
		Assertions.assertEquals("true", response.optString("ok"),
				"Wrong response from TG API, field: ok = false ");
	}

	/**
	 * testing the "getChat" request with String chat ID
	 */
	@Test
	public void stringMethodGetChatShouldReturnedJSONAnswer() {

		String chatId = "871614990";


		JSONObject response = telegramApi.getChat(bot, bot.getToken(), chatId);

		// response must be not null
		Assertions.assertNotNull(response);

		// response must have "ok" status
		Assertions.assertEquals("true", response.optString("ok"),
				"Wrong response from TG API, field: ok = false ");
	}

	/**
	 * testing the "sendMessage" request without keyboard
	 */
	@Test
	public void sendMessageWithOutKeyBoardShouldReturnedJSONAnswer() {
		String chatId = "1029944281";
		String text = "Don`t worry, be happy";
		OutgoingMessage message = new OutgoingMessage();
		message.setStringChatId(chatId);
		message.setText(text);

		JSONObject response = telegramApi.sendMessage(bot, bot.getToken(), message);

		// response must be not null
		Assertions.assertNotNull(response);

		// response must have "ok" status
		Assertions.assertEquals("true", response.optString("ok"),
				"Wrong response from TG API, field: ok = false ");
	}
}
