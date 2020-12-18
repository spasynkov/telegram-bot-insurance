package com.example.telegrambotinsurance.controller;

import com.example.telegrambotinsurance.parameters.TestMessage;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebhookHandlerControllerTest {
	@Autowired
	WebTestClient webTestClient;

	@Value("${bot.insurance.token}")
	String token;

	@Test
	void testReceiveAndProcessMessage_ShouldReturnOkStatus() {
		String uri = "/rest/" + token;

		String expectedMessage = "{\"status\":\"ok\"}";

		String jsonString = TestMessage.OkTextJsonString;

		webTestClient
				.post()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(jsonString))
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.json(expectedMessage);
	}

	@Test
	void testReceiveAndProcessMessage_ShouldReturnStandardJsonException() {
		String uri = "/rest/" + token;

		String message = JSONObject.quote("JSONObject[\"message_id\"] not found.");
		String expectedMessage = String.format("{\"error\":%s}", message);

		String jsonString = TestMessage.missingParameterJsonString;

		webTestClient
				.post()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(jsonString))
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.json(expectedMessage);

		System.out.println();
	}

	@ParameterizedTest
	@MethodSource("com.example.telegrambotinsurance.parameters.TestMessage#provideStringsForTest")
	void testReceiveAndProcessMessage_ShouldReturnOkStatus(String json, String message) {
		String uri = "/rest/" + token;

		String expectedMessage = String.format("{\"error\":%s}", message);

		if (json == null) {
			return;
		}

		webTestClient
				.post()
				.uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(json))
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.json(expectedMessage);
	}
}