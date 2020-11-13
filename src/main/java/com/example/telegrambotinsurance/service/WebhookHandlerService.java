package com.example.telegrambotinsurance.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface WebhookHandlerService {
	// Метод получает бота по токену, преобразует JSONObject в объект Message
	// и предаёт этот объект боту.
	// @return status.
	String receiveAndProcessMessage(String token, JSONObject jsonObject) throws Exception;
}
