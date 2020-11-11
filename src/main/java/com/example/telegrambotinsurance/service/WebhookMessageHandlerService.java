package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Класс обработки все запросов от клиента с телеграм (POST, GET, PUT, DELETE).
 */

@Service
public interface WebhookMessageHandlerService {
	// Метод получает бота по токену, преобразует JSONObject в объект Message
	// и предаёт этот объект бот через метод processMessage
	void convertDataToMessageObjectAndSendItToBot(String token, JSONObject jsonObject) throws BotNotFoundException, JsonProcessingException;
}
