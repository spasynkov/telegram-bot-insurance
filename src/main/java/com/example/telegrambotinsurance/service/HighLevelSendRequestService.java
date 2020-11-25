package com.example.telegrambotinsurance.service;



import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.Message;
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

	public JSONObject sendMessage(AbstractBot bot, String token, Message message) {

		JSONObject jsonObject = sendRequestsService.sendGet(token,
				TGApiRequest.SEND_MESSAGE.getRequest()
						+ TGApiRequest.CHAT_ID.getRequest() + message.getChatId() +
						TGApiRequest.TEXT.getRequest() + message.getText());
		LOGGER.debug(TGApiRequest.SEND_MESSAGE.getRequest());

		return jsonObject;
	}

	public JSONObject sendMessage(AbstractBot bot, String token, String message,
	                              JSONObject keyboard) {
		return new JSONObject();
	}

	public JSONObject getChat(AbstractBot bot, String token, String chatId) {
		JSONObject jsonObject = sendRequestsService.sendGet(token,
				TGApiRequest.GET_CHAT.getRequest() + TGApiRequest.CHAT_ID.getRequest()
						+ chatId);
		LOGGER.debug(TGApiRequest.GET_CHAT.getRequest());
		return jsonObject;
	}

	public JSONObject getChat(AbstractBot bot, String token, long chatId) {
		JSONObject jsonObject = sendRequestsService.sendGet(token,
				TGApiRequest.GET_CHAT.getRequest() + TGApiRequest.CHAT_ID.getRequest()
						+ chatId);
		LOGGER.debug(TGApiRequest.GET_CHAT.getRequest());
		return jsonObject;
	}

	public JSONObject getMe(AbstractBot bot, String token) {
		JSONObject jsonObject = sendRequestsService.sendGet(token,
				TGApiRequest.GET_ME.getRequest());
		LOGGER.debug(TGApiRequest.GET_ME.getRequest());

		return jsonObject;
	}

	private enum TGApiRequest {
		GET_ME("getMe"), SEND_MESSAGE("sendMessage"), GET_CHAT("getChat"),
		CHAT_ID("?chat_id="), TEXT("&text=");


		private String request;

		TGApiRequest(String request) {
			this.request = request;
		}

		public String getRequest() {
			return request;
		}
	}
}
