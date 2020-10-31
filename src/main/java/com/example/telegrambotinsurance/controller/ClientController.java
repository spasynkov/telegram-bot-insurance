package com.example.telegrambotinsurance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * Класс рест контролеов.
 */

@RestController
public class ClientController {


	@PostMapping("/rest/{token}")
	@ResponseBody
	public void getWebhookPostRequest(@PathVariable("token") String token, JSONObject jsonObject) {
		JSONObject d;
		ObjectMapper dd;
	}
}