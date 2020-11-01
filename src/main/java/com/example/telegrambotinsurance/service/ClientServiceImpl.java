package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.Message;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
	private JSONObject jsonObject;
	private  String token;

	public void createMessage(String token, JSONObject jsonObject) {

	}
}
