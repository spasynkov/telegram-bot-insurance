package com.example.telegrambotinsurance.keyboards;

import com.example.telegrambotinsurance.service.SendRequestsService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KeyboardsServiceTest {
	//Здесь Spring вносит значения в нужное поля
	@Autowired
	SendRequestsService sendRequestsService;

	@Autowired
	KeyboardsService keyboardService;

	//Здесь Spring вносит значения в нужное поля
	@Value("${bot.insurance.token}") String token;

	@Test
	void getStarterMenu() {
		JSONObject JSONKeyboard = keyboardService.getStarterMenu();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Test\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
	}

	@Test
	void removeKeyboard(){
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				keyboardService.removeKeyboard());
	}

	@Test
	void getTestInlineKeyboard(){
		JSONObject JSONKeyboard = keyboardService.getTestInlineKeyboard();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Test\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
	}

	@Test
	void getFrequentQuestions() {
		JSONObject JSONKeyboard = keyboardService.getFrequentQuestions();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Test\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
	}

	@Test
	void getExecutionOfTheContract() {
		JSONObject JSONKeyboard = keyboardService.getExecutionOfTheContract();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Test\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
	}

	@Test
	void getTheCode() {
		JSONObject JSONKeyboard = keyboardService.getTheCode();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Test\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
	}

	@Test
	void getSendUserNumber() {
		JSONObject JSONKeyboard = keyboardService.getSendUserNumber();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Test\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
	}
}