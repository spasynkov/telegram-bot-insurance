package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.Message;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WebhookMessageHandlerServiceImpl implements WebhookMessageHandlerService {
	private final BotService botService;

	@Autowired
	public WebhookMessageHandlerServiceImpl(BotService botService) {
		this.botService = botService;
	}

	// Метод получает определённого бота по токену, преобразует JSONObject в объект Message
	// и передаёт этот объект Message полученному боту через метод processMessage
	public String convertDataToMessageObjectAndSendItToBot(String token, JSONObject jsonObject) throws BotNotFoundException {
		// Получение бот по токену.
		AbstractBot bot = botService.findBotByToken(token);

		// Получение значения из объекта JSONObject.
		long message_id = jsonObject.getAsNumber("message_id").longValue();

		long millisecondsForDate = jsonObject.getAsNumber("date").longValue();
		Date date = new Date(millisecondsForDate);

		String text = jsonObject.getAsString("text");

		// Формируется объект Message с полученными параметрами из объекта JSONObject.
		Message message = new Message(message_id, date, text);

		// Передаёт объект Message в метод полученному боту
		bot.processMessage(message);

		return "{\"status\":\"ok\"}";
	}
}
