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
	// и передаёт этот Message объект полученному боту через метод processMessage
	public String convertDataToMessageObjectAndSendItToBot(String token, JSONObject jsonObject) throws BotNotFoundException {
		AbstractBot bot = botService.findBotByToken(token);

		long message_id = jsonObject.getAsNumber("message_id").longValue();

		long millisecondsForDate = jsonObject.getAsNumber("date").longValue();
		Date date = new Date(millisecondsForDate);

		String text = jsonObject.getAsString("text");

		Message message = new Message(message_id, date, text);
		bot.processMessage(message);

		return "{\"status\":\"ok\"}";
	}
}
