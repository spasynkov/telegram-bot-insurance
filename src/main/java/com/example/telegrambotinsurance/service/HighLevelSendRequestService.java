package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.modelbot.AbstractBot;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class HighLevelSendRequestService {

	public JSONObject sendMessage(AbstractBot bot, String token, Integer chatId, String message ){
		return new JSONObject();
	}

	public JSONObject sendMessage(AbstractBot bot, String token, Integer chatId, String message, JSONObject json){
		return new JSONObject();
	}

	public String getChat(Integer chatId){
		String chatName = "Some name";
		return chatName;
	}

	public Integer getChat(String chatName){
		Integer chatId = 0;
		return chatId;
	}
}
