package com.example.telegrambotinsurance.service;



import com.example.telegrambotinsurance.modelbot.AbstractBot;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class HighLevelSendRequestServiceTest {

	@Autowired
	HighLevelSendRequestService highLevelSendRequestService;
	@Autowired
	SendRequestsService sendRequestsService;
	@Value("${bot.insurance.token}")
	String token;
	@Autowired
	@Qualifier("BotsWithTokens")
	Map<String, AbstractBot> bots;

	@Test
	public void methodGetMeShouldReturnedJSONAnswer() {

		AbstractBot bot = bots.get(token);

		JSONObject response = highLevelSendRequestService.getMe(bot, token);

		Assertions.assertNotNull(response);

		Assertions.assertEquals("true", response.getAsString("ok"),
				"Wrong response from TG API, field: ok = false ");
	}

	@Test
	public void integerMethodGetChatShouldReturnedJSONAnswer() {

		Integer chatId = 871614990;

		AbstractBot bot = bots.get(token);

		JSONObject response = highLevelSendRequestService.getChat(bot, token, chatId);

		Assertions.assertNotNull(response);

		Assertions.assertEquals("true", response.getAsString("ok"),
				"Wrong response from TG API, field: ok = false ");
	}

	@Test
	public void stringMethodGetChatShouldReturnedJSONAnswer() {

		String chatId = "871614990";

		AbstractBot bot = bots.get(token);

		JSONObject response = highLevelSendRequestService.getChat(bot, token, chatId);

		Assertions.assertNotNull(response);

		Assertions.assertEquals("true", response.getAsString("ok"),
				"Wrong response from TG API, field: ok = false ");
	}
}
