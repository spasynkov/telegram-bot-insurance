package com.example.telegrambotinsurance.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс рест контролеов.
 */

@RestController
public class BotRestController {

	@PostMapping("/rest/{token}")
	public void getWebHoodPostRequest() {

	}
}
