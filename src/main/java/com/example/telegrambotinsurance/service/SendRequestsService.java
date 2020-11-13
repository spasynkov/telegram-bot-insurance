package com.example.telegrambotinsurance.service;

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
	private static final Logger LOGGER = LoggerFactory.getLogger(SendRequestsService.class);

	//@Value("${service.proxy}")
	//private String proxyValue;

	private static WebClient webClient;

	public SendRequestsService() {
		/*System.out.println(proxyValue);
		if (proxyValue != null || !proxyValue.isEmpty()){
			String proxyIP = proxyValue.split(":")[0];
			int proxyPort = Integer.parseInt(proxyValue.split(":")[1]);
			HttpClient httpClient = HttpClient.create()
					.tcpConfiguration(tcpClient -> tcpClient
							.proxy(proxy -> proxy
									.type(ProxyProvider.Proxy.HTTP)
									.host(proxyIP)
									.port(proxyPort)));
			ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

			webClient = WebClient.builder()
					.clientConnector(connector)
					.baseUrl("https://api.telegram.org")
					.build();
		}
		else{

		 */
			webClient = WebClient.builder()
					.baseUrl("https://api.telegram.org")
					.build();
		//}
	}

	public JSONObject sendGet(String token, String apiMethodName) {

		String uri = "/bot" + token + "/" + apiMethodName;
		LOGGER.debug("https://api.telegram.org" + uri);

		JSONObject response = webClient.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(JSONObject.class)
				.block();

		return response;
	}

	public JSONObject sendPost(String token, String apiMethodName, String jsonBody) {
		String uri = "/bot" + token + "/" + apiMethodName;
		LOGGER.debug("https://api.telegram.org" + uri);

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
