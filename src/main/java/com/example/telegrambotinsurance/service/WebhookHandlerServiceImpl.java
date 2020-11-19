package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.exception.IncomingMessageCheckException;
import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.Message;
import org.json.JSONException;
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

		checkObject(receivedObject);

		JSONObject messageObject = receivedObject.getJSONObject("message");

		int messageId = messageObject.getInt("message_id");
		Date date = new Date(messageObject.getInt("date") * 1000L);
		String text = messageObject.getString("text");
		int chatId = messageObject.getJSONObject("chat").getInt("id");

		Message message = new Message(chatId, messageId, date, text);
		bot.processMessage(message);

		return "{\"status\":\"ok\"}";
	}

	/**
	 * Проверяет существуют ли ключи и объекты, пусты или равны null.
	 *
	 * @param receivedObject JSON объек.
	 * @throws IncomingMessageCheckException Если полученный объект равен null или пустой.
	 *                                       Если блок 'message' в полученном объекте не имеется, равен null или пустой.
	 *                                       Если блок 'text' в полученном объекте не имеется или равен null.
	 */
	private void checkObject(JSONObject receivedObject) {
		isNull(receivedObject);
		isEmpty(receivedObject, "incoming");

		hasObject(receivedObject, "message");
		isNull(receivedObject, "message");

		JSONObject messageObject = receivedObject.getJSONObject("message");

		isEmpty(messageObject, "message");

		hasObject(messageObject, "text");
		isNull(messageObject, "text");
	}

	private void isNull(JSONObject receivedObject) {
		if (receivedObject == null) {
			throw new IncomingMessageCheckException("The incoming object cannot be null.");
		}
	}

	private void isNull(JSONObject receivedObject, String objectName) {
		if (receivedObject.isNull(objectName)) {
			throw new IncomingMessageCheckException("The '" + objectName + "' object cannot be null.");
		}
	}

	private void isEmpty(JSONObject receivedObject, String objectName) {
		if (receivedObject.isEmpty()) {
			throw new IncomingMessageCheckException("The '" + objectName + "' object cannot be empty.");
		}
	}

	private void hasObject(JSONObject receivedObject, String objectName) {
		if (!receivedObject.has(objectName)) {
			throw new IncomingMessageCheckException("There is no '" + objectName + "' block in object.");
		}
	}
}
