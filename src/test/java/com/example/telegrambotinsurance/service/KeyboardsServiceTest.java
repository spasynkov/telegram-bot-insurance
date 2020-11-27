package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.keyboards.InlineKeyboardButton;
import com.example.telegrambotinsurance.keyboards.ReplyKeyboardButton;
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
		keyboardService.createReplyKeyboard(true,true,null);
		keyboardService.addRow();
		keyboardService.addRow();
		keyboardService.addButton(0,0,new ReplyKeyboardButton("Оформить договор"));
		keyboardService.addButton(new ReplyKeyboardButton("Контакты"));
		keyboardService.addButton(1,new ReplyKeyboardButton("Оплата"));
		keyboardService.addButton(new ReplyKeyboardButton("Помощь"));
		JSONObject JSONKeyboard = keyboardService.getKeyboard();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"reply_markup\":" + JSONKeyboard.toString()+"}");
		System.out.println(jsonObject.toString());
	}

	@Test
	void removeKeyboard(){
		JSONObject JSONKeyboard = keyboardService.removeKeyboard();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Delete keyboard\", \"reply_markup\":" + JSONKeyboard.toString() + "}");
	}



	@Test
	void getTestInlineKeyboard(){
		keyboardService.createInlineKeyboard();
		keyboardService.addRow();
		keyboardService.addRow();
		keyboardService.addButton(0, InlineKeyboardButton.builder()
				.text("1").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build());
		keyboardService.addButton(1, InlineKeyboardButton.builder()
				.text("2").url("https://core.telegram.org/bots/api#inlinekeyboardbutton").build());
		JSONObject JSONKeyboard = keyboardService.getKeyboard();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Test\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
	}

	@Test
	void getFrequentQuestions() {
		keyboardService.createReplyKeyboard(true,true,null);
		keyboardService.addRow();
		keyboardService.addRow();
		keyboardService.addButton(0,0, new ReplyKeyboardButton("Частые вопросы"));
		keyboardService.addButton( new ReplyKeyboardButton("1"));
		keyboardService.addButton( new ReplyKeyboardButton("2"));
		keyboardService.addButton(new ReplyKeyboardButton("3"));
		JSONObject JSONKeyboard = keyboardService.getKeyboard();
		JSONObject jsonObject = sendRequestsService.sendPost(token,"sendMessage",
				"{\"chat_id\":-1001484722738, \"text\":\"Test\", \"reply_markup\":" + JSONKeyboard.toString()+"}");
	}
}