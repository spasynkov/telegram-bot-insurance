package com.example.telegrambotinsurance.controller;

import com.example.telegrambotinsurance.service.WebhookHandlerService;
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
public class WebhookHandlerController {
	private final WebhookHandlerService webhookHandlerService;

	@Autowired
	WebhookHandlerController(WebhookHandlerService webhookHandlerService) {
		this.webhookHandlerService = webhookHandlerService;
	}

	// Метод слушает POST запросы по токену определённого бота, обрабатывает полученные сообщения и предаёт их сервису.
	// @return status или error.
	@PostMapping("/rest/{token}")
	public String receiveAndProcessMessage(@PathVariable("token") String token, @RequestBody JSONObject jsonObject) {
		try {
			return webhookHandlerService.receiveAndProcessMessage(token, jsonObject);
		} catch (Exception e) {
			String exceptionAnswer = e.getMessage() == null || e.getMessage().isEmpty() ? e.getClass().getSimpleName() : e.getMessage();
			return String.format("{\"error\":\"%s\"}", exceptionAnswer);
		}
	}
}