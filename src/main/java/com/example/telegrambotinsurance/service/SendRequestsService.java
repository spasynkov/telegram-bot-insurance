package com.example.telegrambotinsurance.service;

//ToDo - заменить на org.json.JSONObject
import net.minidev.json.JSONObject;
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
	private final Logger LOGGER = LoggerFactory.getLogger(SendRequestsService.class);
	private String proxyValue;
	private final String BASEURL = "https://api.telegram.org";

	private WebClient webClient;

	public SendRequestsService(@Value("${proxy:}") String proxyValue) {
		try {
			this.proxyValue = proxyValue;
			if (!this.proxyValue.isEmpty()) {
				String proxyIP = this.proxyValue.split(":")[0];
				int proxyPort = Integer.parseInt(this.proxyValue.split(":")[1]);
				HttpClient httpClient = HttpClient.create()
						.tcpConfiguration(tcpClient -> tcpClient
								.proxy(proxy -> proxy
										.type(ProxyProvider.Proxy.HTTP)
										.host(proxyIP)
										.port(proxyPort)));
				ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

				webClient = WebClient.builder()
						.clientConnector(connector)
						.baseUrl(BASEURL)
						.build();
			} else {
				webClient = WebClient.builder()
						.baseUrl(BASEURL)
						.build();
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			LOGGER.debug("Введены не правильные параметры для прокси-соединения. Соединение запущено напрямую.");
		}
		catch (NumberFormatException e){
			LOGGER.debug("Порт должен быть числом.");
		}
	}

	public JSONObject sendGet(String token, String apiMethodName) {
		String uri = "/bot" + token + "/" + apiMethodName;
		LOGGER.debug(BASEURL + uri);

		JSONObject response = webClient.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(JSONObject.class)
				.block();

		return response;
	}

	public JSONObject sendPost(String token, String apiMethodName, String jsonBody) {
		String uri = "/bot" + token + "/" + apiMethodName;
		LOGGER.debug(BASEURL + uri);

		JSONObject response = webClient.post()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(jsonBody))
				.retrieve()
				.bodyToMono(JSONObject.class)
				.block();

		return response;
	}
}
