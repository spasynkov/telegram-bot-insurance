package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.modelbot.Message;

public interface HandlerWebhookRequestService {
	void processMessage(Message message);
}
