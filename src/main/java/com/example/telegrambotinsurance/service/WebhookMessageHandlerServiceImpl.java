package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.Message;
import org.json.JSONObject;
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
	public String convertDataToMessageObjectAndSendItToBot(String token, JSONObject jsonObject) throws Exception {
		AbstractBot bot = botService.findBotByToken(token);

		if (jsonObject == null) {
			throw new Exception("Преданный jsonObject объект в метод convertDataToMessageObjectAndSendItToBot класса WebhookHandlerServiceImpl равен null.");
		}

		if (jsonObject.isEmpty()) {
			throw new Exception("Предан пустой jsonObject объект в метод convertDataToMessageObjectAndSendItToBot класса WebhookHandlerServiceImpl.");
		}

		if (jsonObject.has("message")) {
			JSONObject messageJsonObject = jsonObject.getJSONObject("message");

			if (!messageJsonObject.isEmpty()) {
				Message message = new Message();

				long message_id = messageJsonObject.getLong("message_id");
				message.setMessage_id(message_id);

				long millisecondsForDate = messageJsonObject.getLong("date");
				Date date = new Date(millisecondsForDate);
				message.setDate(date);

				if (jsonObject.has("text")) {
					String text = messageJsonObject.getString("text");
					message.setText(text);
				}

				bot.processMessage(message);
			}
		}

		return "{\"status\":\"ok\"}";
	}
}
