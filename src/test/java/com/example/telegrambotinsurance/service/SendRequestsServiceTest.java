package com.example.telegrambotinsurance.service;

//ToDo - заменить на org.json.JSONObject
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
		Assertions.assertTrue((boolean)response.get("ok"),"Неправильный ответ от серверов телеграма поле: ok = false");
	}
}