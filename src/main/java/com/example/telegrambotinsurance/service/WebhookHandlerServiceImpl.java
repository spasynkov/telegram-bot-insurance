package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.exception.MessageValidationException;
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
	 * @param token Строка с токеном.
	 * @param json  JSON объект.
	 * @return JSON строку со статусом.
	 * @throws JSONException              Если ключ не найден или если значение не является JSONObject.
	 *                                    Если ключ не найден или значение не может быть преобразовано в выбранный тип.
	 * @throws BotNotFoundException       Если бота с переданным токеном не существует.
	 * @throws MessageValidationException Если полученный объект равен null или пустой.
	 *                                    Если блок 'message' в полученном объекте не имеется, равен null или пустой.
	 *                                    Если блок 'text' в полученном объекте не имеется или равен null.
	 */

	@Override
	public String receiveAndProcessMessage(String token, JSONObject json) {
		AbstractBot bot = botService.findBotByToken(token);

		validate(json);

		JSONObject messageBlock = json.getJSONObject("message");

		int messageId = messageBlock.getInt("message_id");
		Date date = new Date(messageBlock.getInt("date") * 1000L);
		String text = messageBlock.getString("text");
		long chatId = messageBlock.getJSONObject("chat").getLong("id");

		Message message = new Message(chatId, messageId, date, text);
		bot.processMessage(message);

		return "{\"status\":\"ok\"}";
	}

	/**
	 * Проверяет существуют ли ключи и объекты, пусты или равны null.
	 *
	 * @param json
	 *        JSON объект.
	 *
	 * @throws MessageValidationException
	 *         Если полученный объект равен null или пустой.
	 *         Если блок 'message' в полученном объекте не имеется, равен null или пустой.
	 *         Если блок 'text' в полученном объекте не имеется или равен null.
	 */

	private void validate(JSONObject json) {
		isNull(json);
		isEmpty(json, "incoming");

		hasObject(json, "message");
		isNull(json, "message");

		JSONObject messageBlock = json.getJSONObject("message");
		isEmpty(messageBlock, "message");

		hasObject(messageBlock, "text");
		isNull(messageBlock, "text");
	}

	private void isNull(JSONObject json) {
		if (json == null) {
			throw new MessageValidationException("The incoming object cannot be null.");
		}
	}

	private void isNull(JSONObject json, String key) {
		if (json.isNull(key)) {
			throw new MessageValidationException("The '" + key + "' object cannot be null.");
		}
	}

	private void isEmpty(JSONObject json, String key) {
		if (json.isEmpty()) {
			throw new MessageValidationException("The '" + key + "' object cannot be empty.");
		}
	}

	private void hasObject(JSONObject json, String key) {
		if (!json.has(key)) {
			throw new MessageValidationException("There is no '" + key + "' block in object.");
		}
	}
}
