package com.example.telegrambotinsurance.exception;

public class IncomingMessageCheckException extends RuntimeException {
	public IncomingMessageCheckException() {
	}

	public IncomingMessageCheckException(String message) {
		super(message);
	}
}
