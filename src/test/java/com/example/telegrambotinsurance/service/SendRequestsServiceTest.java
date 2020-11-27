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
	//Здесь Spring вносит значения в нужное поля
	@Autowired
	SendRequestsService sendRequestsService;

	//Здесь Spring вносит значения в нужное поля
	@Value("${bot.insurance.token}") String token;

	/**
	 * Проверка ответа от метода sendGet
	 */
	/*@Test
	void sendGet() {
		JSONObject response = sendRequestsService.sendGet(token,"getMe");
		//Проверка, что ответ не пустой
		Assertions.assertNotNull(response);
		//Проверка, что ответ удачный
		Assertions.assertTrue("true".equals(response.getAsString("ok")),"Wrong response from telegram servers, field: ok = false");
	}

	/**
	 * Проверка ответа от метода sendGet и отправка сообщения "Testing sendPost()"
	 *
	//@Test
	void sendPost() {
		//JSONObject response = sendRequestsService.sendPost(token,"sendMessage",
		//		"{\"chat_id\":-1001484722738, \"text\":\"Testing sendPost()\"}");
		//Проверка, что ответ не пустой
		Assertions.assertNotNull(response);
		//Проверка, что ответ удачный
		Assertions.assertTrue("true".equals(response.getAsString("ok")),"Wrong response from telegram servers, field: ok = false");
	}

	 */
}