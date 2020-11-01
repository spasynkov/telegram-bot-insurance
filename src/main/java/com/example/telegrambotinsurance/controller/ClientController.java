package com.example.telegrambotinsurance.controller;

import com.example.telegrambotinsurance.service.ClientService;
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
	public void getClientPostRequest(@PathVariable("token") String token, JSONObject jsonObject) {
		clientService.createMessage(token, jsonObject);
	}
}