package com.example.telegrambotinsurance.controller;

import com.example.telegrambotinsurance.service.WebhookMessageHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * Класс обработки сообщений пришедших на вебхук определённого бота.
 */

@RestController
public class WebhookMessageHandlerController {
	private WebhookMessageHandlerService webhookMessageHandlerService;

	@Autowired
	WebhookMessageHandlerController(WebhookMessageHandlerService webhookMessageHandlerService) {
		this.webhookMessageHandlerService = webhookMessageHandlerService;
	}

	// Метод слушает POST запросы по токену определённого бота, обрабатывает полученные сообщения и предаёт их сервису.
	@PostMapping("/rest/{token}")
	public String receiveAndProcessIncomingMessage(@PathVariable("token") String token, JSONObject jsonObject) {
		try {
			webhookMessageHandlerService.convertDataToMessageObjectAndSendItBot(token, jsonObject);
			return "{\"status\":\"ok\"}";
		}
		catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() +"\"}";
		}
	}
}