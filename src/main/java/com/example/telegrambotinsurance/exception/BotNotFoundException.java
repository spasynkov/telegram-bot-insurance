package com.example.telegrambotinsurance.exception;

public class BotNotFoundException extends Exception {
	public BotNotFoundException() {
	}

	public BotNotFoundException(String message) {
		super(message);
	}
}
