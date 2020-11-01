package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.BotNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
	void createMessage(String token, JSONObject jsonObject) throws BotNotFoundException, JsonProcessingException;
}
