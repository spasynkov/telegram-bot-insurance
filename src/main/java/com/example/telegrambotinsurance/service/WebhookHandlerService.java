package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.exception.IncomingMessageCheckException;
import org.json.JSONObject;

public interface WebhookHandlerService {
	/**
	 * Определяет бота по токену, преобразует JSONObject в объект Message
	 * и передаёт этот Message объект полученному боту.
	 *
	 * @param token          Строка с токиеном.
	 * @param receivedObject JSON объек.
	 * @return JSON строку со статусом.
	 * @throws BotNotFoundException          Если бота с преданным токеном не существует.
	 * @throws IncomingMessageCheckException Если полученный объект равен null или пустой.
	 *                                       Если объект 'message' в полученном объекте равен null, пустой или его не имеется.
	 *                                       Если объект 'text' в полученном объекте равен null или его не имеется.
	 */
	String receiveAndProcessMessage(String token, JSONObject receivedObject);
}
