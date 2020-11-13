package com.example.telegrambotinsurance.service;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.ProxyProvider;

@Service
public class SendRequestsService {
	public JSONObject sendGet(String token, String apiMethodName) {
		HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(tcpClient -> tcpClient
						.proxy(proxy -> proxy
								.type(ProxyProvider.Proxy.HTTP)
								.host("138.197.106.52")
								.port(80)));
		ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

		WebClient webClient = WebClient.builder()
				.clientConnector(connector)
				.baseUrl("https://api.telegram.org")
				.build();

		JSONObject response = webClient.get()
								.uri("/bot" + token + "/" + apiMethodName)
								.retrieve()
								.bodyToMono(JSONObject.class)
								.block();

		return response;
	}

	public JSONObject sendPost(String token, String apiMethodName, String jsonBody) {
		HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(tcpClient -> tcpClient
						.proxy(proxy -> proxy
								.type(ProxyProvider.Proxy.HTTP)
								.host("138.197.106.52")
								.port(80)));
		ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

		WebClient webClient = WebClient.builder()
				.clientConnector(connector)
				.baseUrl("https://api.telegram.org")
				.build();

		JSONObject response = webClient.post()
				.uri("/bot" + token + "/" + apiMethodName)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(jsonBody))
				.retrieve()
				.bodyToMono(JSONObject.class)
				.block();

		return response;
	}
}
