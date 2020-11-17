package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.exception.IncomingMessageCheckException;
import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.Message;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WebhookHandlerServiceImpl implements WebhookHandlerService {
	private final BotService botService;

	@Autowired
	public WebhookHandlerServiceImpl(BotService botService) {
		this.botService = botService;
	}

	/**
	 * Определяет бота по токену, преобразует JSONObject в объект Message
	 * и передаёт этот Message объект полученному боту.
	 *
	 * @param token          Строка с токиеном.
	 * @param receivedObject JSON объек.
	 * @return JSON строку со статусом.
	 * @throws BotNotFoundException          Если бота с преданным токеном не существует.
	 * @throws IncomingMessageCheckException Если полученный объект равен null или пустой.
	 *                                       Если объект 'message' в полученном объекте равен null или его не имеется.
	 */
	@Override
	public String receiveAndProcessMessage(String token, JSONObject receivedObject) {
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

				if (messageJsonObject.has("text") && !messageJsonObject.isNull("text")) {
					String text = messageJsonObject.getString("text");
					message.setText(text);
				}

				bot.processMessage(message);
			}
		}

		return "{\"status\":\"ok\"}";
	}
}
