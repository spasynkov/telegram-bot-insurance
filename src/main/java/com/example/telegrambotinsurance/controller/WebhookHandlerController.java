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
	public WebhookHandlerController(WebhookHandlerService webhookHandlerService) {
		this.webhookHandlerService = webhookHandlerService;
	}

	/**
	 * Слушает запросы вебхука бота и предаёт полученные сообщения и токен сервису.
	 *
	 * @param token       Строка с токиеном.
	 * @param requestBody Объект JSONObject, содержащий тело сообщения.
	 * @return Возвращает JSON строку, содержащую статус выполнения запроса.
	 */
	@PostMapping("/rest/{token}")
	public String receiveAndProcessMessage(@PathVariable("token") String token, @RequestBody JSONObject requestBody) {
		try {
			return webhookHandlerService.receiveAndProcessMessage(token, requestBody);
		} catch (Exception e) {
			String exceptionAnswer = e.getMessage() == null || e.getMessage().isEmpty() ? e.getClass().getSimpleName() : e.getMessage();
			return String.format("{\"error\":\"%s\"}", exceptionAnswer);
		}
	}
}