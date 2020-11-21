package com.example.telegrambotinsurance.service;


import com.example.telegrambotinsurance.modelbot.AbstractBot;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HighLevelSendRequestService {

	private final Logger LOGGER = LoggerFactory.getLogger(HighLevelSendRequestService.class);
	SendRequestsService sendRequestsService;

	@Autowired
	public HighLevelSendRequestService(SendRequestsService sendRequestsService) {
		this.sendRequestsService = sendRequestsService;
	}

	public JSONObject sendMessage(AbstractBot bot, String token, Integer chatId, String message) {
		return new JSONObject();
	}

	public JSONObject sendMessage(AbstractBot bot, String token, Integer chatId, String message, JSONObject json) {
		return new JSONObject();
	}

	public String getChat(Integer chatId) {
		String chatName = "Some name";
		return chatName;
	}

	public Integer getChat(String chatName) {
		Integer chatId = 0;
		return chatId;
	}

	public JSONObject getMe(AbstractBot bot, String token) {
		JSONObject jsonObject = sendRequestsService.sendGet(token, TGApiRequest.GET_ME.getRequest());
		LOGGER.debug(TGApiRequest.GET_ME.getRequest());

		return jsonObject;
	}

	public enum TGApiRequest {
		GET_ME("getMe"), SEND_MESSAGE("sendMessage"), GET_CHAT("getChat");

		private String request;

		TGApiRequest(String request) {
			this.request = request;
		}

		public String getRequest() {
			return request;
		}
	}
}
