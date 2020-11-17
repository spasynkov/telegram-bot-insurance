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

		if (receivedObject == null) {
			throw new IncomingMessageCheckException("The object passed to the method is null.");
		}

		if (receivedObject.isEmpty()) {
			throw new IncomingMessageCheckException("The object passed to the method is empty.");
		}

		if (!receivedObject.has("message")) {
			throw new IncomingMessageCheckException("The object passed to the method does not contain a 'message'.");
		}

		if (receivedObject.isNull("message")) {
			throw new IncomingMessageCheckException("The 'message' of the object passed to the method is null.");
		}

		JSONObject messageObject = receivedObject.getJSONObject("message");
		Message message = createMessageObject(messageObject);

		bot.processMessage(message);

		return "{\"status\":\"ok\"}";
	}

	/**
	 * Создаёт объект класса Message.
	 *
	 * @param messageObject JSON объек.
	 * @return Объект с класса Message.
	 * @throws IncomingMessageCheckException Если полученный объект пустой.
	 *                                       Если объект 'text' в полученном объекте равен null или его не имеется.
	 */
	private Message createMessageObject(JSONObject messageObject) {
		if (messageObject.isEmpty()) {
			throw new IncomingMessageCheckException("The 'message' of the object passed to the method is empty.");
		}

		if (!messageObject.has("text")) {
			throw new IncomingMessageCheckException("The object passed to the method does not contain a 'text'.");
		}

		if (messageObject.isNull("text")) {
			throw new IncomingMessageCheckException("The 'text' of the object passed to the method is null.");
		}

		int messageId = messageObject.getInt("message_id");

		long millisecondsForDate = messageObject.getLong("date") * 1000L;
		Date date = new Date(millisecondsForDate);

		String text = messageObject.getString("text");

		int chatId = messageObject.getJSONObject("chat").getInt("id");

		return new Message(chatId, messageId, date, text);
	}
}
