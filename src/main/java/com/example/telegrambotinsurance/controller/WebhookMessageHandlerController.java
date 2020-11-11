package com.example.telegrambotinsurance.controller;

import com.example.telegrambotinsurance.service.WebhookMessageHandlerService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
			webhookMessageHandlerService.convertDataToMessageObjectAndSendItToBot(token, jsonObject);
			return "{\"status\":\"ok\"}";
		}
		catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() +"\"}";
		}
	}
}