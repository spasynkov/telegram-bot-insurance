package com.example.telegrambotinsurance.controller;

import com.example.telegrambotinsurance.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * Класс обработки всез запросов от клиета с телеграм (POST, GET, PUT, DELETE).
 */

@RestController
public class ClientController {
	private ClientService clientService;

	@Autowired
	ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping("/rest/{token}")
	public String getClientPostRequest(@PathVariable("token") String token, JSONObject jsonObject) {
		try {
			clientService.createMessage(token, jsonObject);
			return "{\"status\":\"ok\"}";
		}
		catch (Exception e) {
			String error = "ОК";
			return "{\"error\":\"" + e.getMessage() +"\"}";
		}
	}
}