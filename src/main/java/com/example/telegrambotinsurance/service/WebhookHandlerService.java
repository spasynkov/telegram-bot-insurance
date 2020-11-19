package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.exception.IncomingMessageCheckException;
import org.json.JSONException;
import org.json.JSONObject;

public interface WebhookHandlerService {
	/**
	 * Определяет бота по токену, преобразует JSONObject в объект Message
	 * и передаёт этот Message объект полученному боту.
	 *
	 * @param token          Строка с токиеном.
	 * @param receivedObject JSON объек.
	 * @return JSON строку со статусом.
	 * @throws JSONException                 Если ключ не найден или если значение не является JSONObject.
	 *                                       Если ключ не найден или значение не может быть преобразовано в выбранный тип.
	 * @throws BotNotFoundException          Если бота с переданным токеном не существует.
	 * @throws IncomingMessageCheckException Если полученный объект равен null или пустой.
	 *                                       Если блок 'message' в полученном объекте не имеется, равен null или пустой.
	 *                                       Если блок 'text' в полученном объекте не имеется или равен null.
	 */
	String receiveAndProcessMessage(String token, JSONObject receivedObject);
}
