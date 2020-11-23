package com.example.telegrambotinsurance.exception;

public class BotNotFoundException extends RuntimeException {
	public BotNotFoundException() {
	}

	public BotNotFoundException(String message) {
		super(message);
	}
}
