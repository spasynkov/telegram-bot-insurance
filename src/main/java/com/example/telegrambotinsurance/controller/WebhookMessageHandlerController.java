package com.example.telegrambotinsurance.controller;

import com.example.telegrambotinsurance.service.WebhookMessageHandlerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс обработки сообщений пришедших на вебхук определённого бота.
 */

@RestController
public class WebhookMessageHandlerController {
	private final WebhookMessageHandlerService webhookMessageHandlerService;

	@Autowired
	WebhookMessageHandlerController(WebhookMessageHandlerService webhookMessageHandlerService) {
		this.webhookMessageHandlerService = webhookMessageHandlerService;
	}

	// Метод слушает POST запросы по токену определённого бота, обрабатывает полученные сообщения и предаёт их сервису.
	@PostMapping("/rest/{token}")
	public String receiveAndProcessIncomingMessage(@PathVariable("token") String token, @RequestBody JSONObject jsonObject) {
		try {
			return webhookMessageHandlerService.convertDataToMessageObjectAndSendItToBot(token, jsonObject);
		}
		catch (Exception e) {
			String exceptionAnswer = e.getMessage() == null || e.getMessage().isEmpty() ? e.getClass().getSimpleName() : e.getMessage();
			return String.format("{\"error\":\"%s\"}", exceptionAnswer);
		}
	}
}