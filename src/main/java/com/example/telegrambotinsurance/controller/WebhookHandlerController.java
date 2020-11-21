package com.example.telegrambotinsurance.controller;

import com.example.telegrambotinsurance.service.WebhookHandlerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс ожидания оповещения от webhook.
 */

@RestController
public class WebhookHandlerController {
	private final WebhookHandlerService webhookHandlerService;

	@Autowired
	public WebhookHandlerController(WebhookHandlerService webhookHandlerService) {
		this.webhookHandlerService = webhookHandlerService;
	}

	/**
	 * Ожидает оповещения от webhook, при получении сообщения передаёт их в сервис для обработки.
	 *
	 * @param token       Строка с токеном.
	 * @param requestBody Объект JSONObject, содержащий тело сообщения.
	 * @return Возвращает JSON строку, содержащую статус выполнения запроса.
	 */

	@PostMapping("/rest/{token}")
	public String receiveAndProcessMessage(@PathVariable("token") String token, @RequestBody JSONObject requestBody) {
		try {
			return webhookHandlerService.receiveAndProcessMessage(token, requestBody);
		} catch (Exception e) {
			String exceptionAnswer = e.getMessage() == null || e.getMessage().isEmpty() ?
					e.getClass().getSimpleName() : e.getMessage();
			exceptionAnswer = JSONObject.quote(exceptionAnswer);
			return String.format("{\"error\":%s}", exceptionAnswer);
		}
	}
}