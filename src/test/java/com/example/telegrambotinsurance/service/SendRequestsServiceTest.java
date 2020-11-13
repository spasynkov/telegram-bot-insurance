package com.example.telegrambotinsurance.service;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SendRequestsServiceTest {
	@Autowired
	SendRequestsService sendRequestsService;

	@Value("${bot.insurance.token}") String token;
	@Test
	void sendGet() {
		JSONObject response = sendRequestsService.sendGet(token,"getMe");
		Assertions.assertNotNull(response);
	}

	@Test
	void sendPost() {
		JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Testing sendPost()\"}");
		Assertions.assertNotNull(response);
	}
}