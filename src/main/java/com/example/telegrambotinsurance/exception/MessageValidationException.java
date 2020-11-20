package com.example.telegrambotinsurance.exception;

public class MessageValidationException extends RuntimeException {
	public MessageValidationException() {
	}

	public MessageValidationException(String message) {
		super(message);
	}
}
