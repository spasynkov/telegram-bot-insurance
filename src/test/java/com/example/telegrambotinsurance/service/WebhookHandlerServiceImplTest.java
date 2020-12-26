package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.exception.MessageValidationException;
import com.example.telegrambotinsurance.parameters.TestMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WebhookHandlerServiceImplTest {
	@Autowired
	WebhookHandlerServiceImpl webhookHandlerService;

	@Value("${bot.insurance.token}")
	String token;

	@Test
	void testReceiveAndProcessMessage_ShouldReturnOkStatus() {
		String jsonString = TestMessage.OkTextJsonString;
		JSONObject source = new JSONObject();

		try {
			source = new JSONObject(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String expected = "{\"status\":\"ok\"}";
		String actual = webhookHandlerService.receiveAndProcessMessage(token, source);

		assertEquals(expected, actual, "The OK status test failed.");
	}

	@Test
	void testReceiveAndProcessMessage_ShouldThrowsStandardJsonException() {
		String jsonString = TestMessage.missingParameterJsonString;

		Exception exception = assertThrows(JSONException.class, () -> {
					final JSONObject source = new JSONObject(jsonString);
					webhookHandlerService.receiveAndProcessMessage(token, source);
				}
		);
		String actualMessage = exception.getMessage();

		String expectedMessage = "JSONObject[\"message_id\"] not found.";

		assertEquals(actualMessage, expectedMessage, "The test '" + expectedMessage + "' failed.");
	}

	@ParameterizedTest
	@MethodSource("com.example.telegrambotinsurance.parameters.TestMessage#provideStringsForTest")
	void testReceiveAndProcessMessage_ShouldThrowsMessageException(String json, String expectedMessage) {
		JSONObject source = json == null ? null : new JSONObject(json);

		Exception exception = assertThrows(MessageValidationException.class, () ->
				webhookHandlerService.receiveAndProcessMessage(token, source)
		);
		String actualMessage = exception.getMessage();

		assertEquals(actualMessage, expectedMessage, "The test '" + expectedMessage + "' failed.");
	}
}