package com.example.telegrambotinsurance.service;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
	void createMessage(String token, JSONObject jsonObject);
}
