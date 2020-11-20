package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.modelbot.AbstractBot;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class HighLevelSendRequestService {

	private final String GET_ME = "getMe";
	SendRequestsService sendRequestsService;
	private final Logger LOGGER = LoggerFactory.getLogger(HighLevelSendRequestService.class);

	@Autowired
	public HighLevelSendRequestService(SendRequestsService sendRequestsService) {
		this.sendRequestsService = sendRequestsService;
	}

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

	public JSONObject getMe(AbstractBot bot, String token){
		JSONObject jsonObject = sendRequestsService.sendGet(token, GET_ME);
		LOGGER.debug(GET_ME);

		return jsonObject;
	}
}
