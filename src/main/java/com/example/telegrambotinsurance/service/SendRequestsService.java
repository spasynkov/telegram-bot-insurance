package com.example.telegrambotinsurance.service;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.ProxyProvider;

@Service
public class SendRequestsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SendRequestsService.class);
	private final String baseUrl = "https://api.telegram.org";      // Базовая URL телеграма
	private WebClient webClient;

	public SendRequestsService(@Value("${proxy:}") String proxyValue) {
		try {
			if (!proxyValue.isEmpty()) { // Проверка используется ли прокси или нет
				String proxyIP = proxyValue.split(":")[0];      // Строка с IP и портом
				int proxyPort = Integer.parseInt(proxyValue.split(":")[1]);
				createProxifiedWebClient(proxyIP, proxyPort);
			} else {
				createDefaultWebClient();
			}
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			// ArrayIndexOutOfBoundsException, если пользователь не правильно ввел прокси
			// NumberFormatException, если пользователь ввел порт НЕ цифрами
			String messageUnableToSetProxy = "Unable to set proxy. Using direct connection for requests.";
			LOGGER.debug(messageUnableToSetProxy);
			createDefaultWebClient();
		}
	}

	/**
	 * Метод для отправки GET-запроса на телеграм
	 *
	 * @param token
	 * @param apiMethodName
	 * @return JSONObject - ответ от телеграма
	 */
	public JSONObject sendGet(String token, String apiMethodName) {
		String uri = "/bot" + token + "/" + apiMethodName;

		//Получение ответа от телеграма
		String strResponse = webClient.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(String.class)
				.block();

		JSONObject response = new JSONObject(strResponse);

		return response;

	}

	/**
	 * Метод для отправки POST-запроса на телеграм
	 *
	 * @param token
	 * @param apiMethodName
	 * @param jsonBody
	 * @return JSONObject - ответ от телеграма
	 */
	public JSONObject sendPost(String token, String apiMethodName, String jsonBody) {
		String uri = "/bot" + token + "/" + apiMethodName;

		//Получение ответа от телеграма
		String strResponse = webClient.post()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(jsonBody))
				.retrieve()
				.bodyToMono(String.class)
				.block();

		JSONObject response = new JSONObject(strResponse);

		return response;
	}

	/**
	 * Создание прямого подключения к телеграму
	 */
	public void createDefaultWebClient() {
		webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.build();
	}

	/**
	 * Создание прокси соединения к телеграму
	 *
	 * @param proxyIP   - IP адрес прокси
	 * @param proxyPort - порт прокси
	 */
	public void createProxifiedWebClient(String proxyIP, int proxyPort) {
		HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(tcpClient -> tcpClient
						.proxy(proxy -> proxy
								.type(ProxyProvider.Proxy.HTTP)
								.host(proxyIP)
								.port(proxyPort)));
		ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

		webClient = WebClient.builder()
				.clientConnector(connector)
				.baseUrl(baseUrl)
				.build();
	}
}
