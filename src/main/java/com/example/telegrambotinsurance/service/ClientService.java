package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
	// Метод получает бота оп токену, преобразует  JSONObject в объект Message
	// и предаёт этот объект бот через метод processMessage
	void createMessage(String token, JSONObject jsonObject) throws BotNotFoundException, JsonProcessingException;
}
