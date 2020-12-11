package com.example.telegrambotinsurance.service;


import com.example.telegrambotinsurance.modelbot.AbstractBot;
import com.example.telegrambotinsurance.modelbot.OutgoingMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TelegramApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(TelegramApi.class);
	/**
	 * instance of a class for sending low-level requests to the telegram API
	 */
	SendRequestsService sendRequestsService;

	/**
	 * constructor for implementing an instance of a low-level
	 * class for creating requests to the telegram API
	 */
	@Autowired
	public TelegramApi(SendRequestsService sendRequestsService) {
		this.sendRequestsService = sendRequestsService;
	}

	/**
	 * Method for sending a message to the telegram API without using the keyboard
	 *
	 * @param bot     an instance of the bot
	 * @param token   a token of the bot
	 * @param message message text
	 * @return response from the API telegram
	 */
	public JSONObject sendMessage(AbstractBot bot, String token, OutgoingMessage message) {

		JSONObject jsonObject = sendRequestsService.sendGet(token,
				TGApiRequest.SEND_MESSAGE.getRequest()
						+ TGApiRequest.CHAT_ID.getRequest() + message.getStringChatId() +
						TGApiRequest.TEXT.getRequest() + message.getText());
		LOGGER.debug(TGApiRequest.SEND_MESSAGE.getRequest());

		return jsonObject;
	}

	/**
	 * Method for sending a message to the telegram API with using the keyboard
	 *
	 * @param bot      an instance of the bot
	 * @param message  message text
	 * @param keyboard an instance of keyboard
	 * @return response from the API telegram
	 */
	//TODO implement the sendMessage method with the keyboard!!!
	public JSONObject sendMessage(AbstractBot bot, OutgoingMessage message,
	                              JSONObject keyboard) {
		return new JSONObject();
	}

	/**
	 * this method returns information about the chat
	 *
	 * @param bot    an instance of the bot
	 * @param token  a token of the bot
	 * @param chatId String type chat ID
	 * @return response from the API telegram
	 */
	public JSONObject getChat(AbstractBot bot, String token, String chatId) {
		JSONObject jsonObject = sendRequestsService.sendGet(token,
				TGApiRequest.GET_CHAT.getRequest() + TGApiRequest.CHAT_ID.getRequest()
						+ chatId);
		LOGGER.debug(TGApiRequest.GET_CHAT.getRequest());
		return jsonObject;
	}

	/**
	 * this method returns information about the chat
	 *
	 * @param bot    an instance of the bot
	 * @param token  a token of the bot
	 * @param chatId long type chat ID
	 * @return response from the API telegram
	 */
	public JSONObject getChat(AbstractBot bot, String token, long chatId) {
		JSONObject jsonObject = sendRequestsService.sendGet(token,
				TGApiRequest.GET_CHAT.getRequest() + TGApiRequest.CHAT_ID.getRequest()
						+ chatId);
		LOGGER.debug(TGApiRequest.GET_CHAT.getRequest());
		return jsonObject;
	}

	/**
	 * this method returns basic information about the bot
	 *
	 * @param bot   an instance of the bot
	 * @param token a token of the bot
	 * @return response from the API telegram
	 */
	public JSONObject getMe(AbstractBot bot, String token) {
		JSONObject jsonObject = sendRequestsService.sendGet(token,
				TGApiRequest.GET_ME.getRequest());
		LOGGER.debug(TGApiRequest.GET_ME.getRequest());

		return jsonObject;
	}

	/**
	 * class for generality of composing the http request string
	 */
	private enum TGApiRequest {
		GET_ME("getMe"), SEND_MESSAGE("sendMessage"), GET_CHAT("getChat"),
		CHAT_ID("?chat_id="), TEXT("&text=");

		/**
		 * this field contains the value of the enum used
		 */
		private final String request;

		/**
		 * constructor
		 *
		 * @param request value of the enum used
		 */
		TGApiRequest(String request) {
			this.request = request;
		}

		/**
		 * this method returns value of the enum used
		 *
		 * @return value of  the enum used
		 */
		public String getRequest() {
			return request;
		}
	}
}
