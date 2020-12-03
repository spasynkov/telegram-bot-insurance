package com.example.telegrambotinsurance.service;


import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.OutgoingMessage;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class HighLevelSendRequestServiceTest {

	/**
	 * fields and class instances required for testing sending
	 * and receiving a response from the telegram API using spring boot
	 */
	@Autowired
	HighLevelSendRequestService highLevelSendRequestService;
	@Autowired
	SendRequestsService sendRequestsService;
	@Value("${bot.insurance.token}")
	String token;
	@Autowired
	@Qualifier("BotsWithTokens")
	Map<String, AbstractBot> bots;

	/**
	 * testing the "getMe" request
	 */
	@Test
	public void methodGetMeShouldReturnedJSONAnswer() {

		AbstractBot bot = bots.get(token);

		JSONObject response = highLevelSendRequestService.getMe(bot, token);

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

		AbstractBot bot = bots.get(token);

		JSONObject response = highLevelSendRequestService.getChat(bot, token, chatId);

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

		AbstractBot bot = bots.get(token);

		JSONObject response = highLevelSendRequestService.getChat(bot, token, chatId);

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
		AbstractBot bot = bots.get(token);
		OutgoingMessage message = new OutgoingMessage();
		message.setStringChatId(chatId);
		message.setText(text);

		JSONObject response = highLevelSendRequestService.sendMessage(bot, token, message);

		// response must be not null
		Assertions.assertNotNull(response);

		// response must have "ok" status
		Assertions.assertEquals("true", response.optString("ok"),
				"Wrong response from TG API, field: ok = false ");
	}
}
