package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
	private BotService botService;

	@Autowired
	public ClientServiceImpl(BotService botService) {
		this.botService = botService;
	}

	// Метод получает бота по токену, преобразует JSONObject в объект Message
	// и предаёт этот объект бот через метод processMessage
	public void createMessage(String token, JSONObject jsonObject) throws BotNotFoundException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();

		// Получаем бот по токену.
		AbstractBot bot = botService.findBotByToken(token);

		// Получаем объект сообщение Message из объекта JSONObject.
		String jsonString = jsonObject.toString();
		Message message = objectMapper.readValue(jsonString, Message.class);

		bot.processMessage(message);
	}
}
