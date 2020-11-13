package com.example.telegrambotinsurance.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface WebhookMessageHandlerService {
	// Метод получает бота по токену, преобразует JSONObject в объект Message
	// и предаёт этот объект боту через метод processMessage
	String convertDataToMessageObjectAndSendItToBot(String token, JSONObject jsonObject) throws Exception;
}
