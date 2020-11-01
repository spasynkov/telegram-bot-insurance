package com.example.telegrambotinsurance.controller;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.example.telegrambotinsurance.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * Класс рест контролеов.
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
		catch (BotNotFoundException | JsonProcessingException e) {
			String error = "ОК";
			return "{\"error\":\"" + e +"\"}";
		}
	}
}