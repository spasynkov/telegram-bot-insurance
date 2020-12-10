package com.example.telegrambotinsurance.parameters;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestMessage {
	public final static String OkTextJsonString = "{" +
			"\"update_id\": 641170930," +
			"\"message\": {" +
			"\"message_id\": 785," +
			"\"from\": {" +
			"\"id\": 652216725," +
			"\"is_bot\": false," +
			"\"first_name\": \"Stas\"," +
			"\"last_name\": \"Pasynkov\"," +
			"\"username\": \"spasynkov\"," +
			"\"language_code\": \"uk\"" +
			"}," +
			"\"chat\": {" +
			"\"id\": -485469147," +
			"\"title\":\"\\u0420\\u0430\\u0437\\u0440\\u0430\\u0431\\u043e\\u0442\\u043a\\u0430 \\u0442\\u0435\\u043b\\u0435\\u0433\\u0440\\u0430\\u043c-\\u0431\\u043e\\u0442\\u0430\"," +
			"\"type\": \"group\"," +
			"\"all_members_are_administrators\":false" +
			"}," +
			"\"date\": 1586690677," +
			"\"text\": \"\\u0440\\u0430\\u0441\\u0448\\u0430\\u0442\\u0430\\u043b\\u0438\\u0441\\u044c \\u043a \\u0447\\u0435\\u0440\\u0442\\u0443 \\u043d\\u0435\\u0440\\u0432\\u044b\"" +
			"}" +
			"}";

	public final static String missingParameterJsonString = "{" +
			"\"update_id\": 641170930," +
			"\"message\": {" +
			"\"from\": {" +
			"\"id\": 652216725," +
			"\"is_bot\": false," +
			"\"first_name\": \"Stas\"," +
			"\"last_name\": \"Pasynkov\"," +
			"\"username\": \"spasynkov\"," +
			"\"language_code\": \"uk\"" +
			"}," +
			"\"chat\": {" +
			"\"id\": -485469147," +
			"\"title\":\"\\u0420\\u0430\\u0437\\u0440\\u0430\\u0431\\u043e\\u0442\\u043a\\u0430 \\u0442\\u0435\\u043b\\u0435\\u0433\\u0440\\u0430\\u043c-\\u0431\\u043e\\u0442\\u0430\"," +
			"\"type\": \"group\"," +
			"\"all_members_are_administrators\":false" +
			"}," +
			"\"date\": 1586690677," +
			"\"text\": \"\\u0440\\u0430\\u0441\\u0448\\u0430\\u0442\\u0430\\u043b\\u0438\\u0441\\u044c \\u043a \\u0447\\u0435\\u0440\\u0442\\u0443 \\u043d\\u0435\\u0440\\u0432\\u044b\"" +
			"}" +
			"}";

	public static Stream<Arguments> provideStringsForTest() {
		return Stream.of(
				Arguments.of(null, "The incoming object cannot be null."),
				Arguments.of("{}", "The 'incoming' object cannot be empty."),
				Arguments.of("{" +
								"\"update_id\": 641170930," +
								"}",
						"There is no 'message' block in object."),
				Arguments.of("{" +
								"\"update_id\": 641170930," +
								"\"message\": " + null +
								"}",
						"The 'message' object cannot be null."),
				Arguments.of("{" +
								"\"update_id\": 641170930," +
								"\"message\": {}" +
								"}",
						"The 'message' object cannot be empty."),
				Arguments.of("{" +
								"\"update_id\": 641170930," +
								"\"message\": {" +
								"\"message_id\": 785," +
								"\"from\": {" +
								"\"id\": 652216725," +
								"\"is_bot\": false," +
								"\"first_name\": \"Stas\"," +
								"\"last_name\": \"Pasynkov\"," +
								"\"username\": \"spasynkov\"," +
								"\"language_code\": \"uk\"" +
								"}," +
								"\"chat\": {" +
								"\"id\": -485469147," +
								"\"title\":\"\\u0420\\u0430\\u0437\\u0440\\u0430\\u0431\\u043e\\u0442\\u043a\\u0430 \\u0442\\u0435\\u043b\\u0435\\u0433\\u0440\\u0430\\u043c-\\u0431\\u043e\\u0442\\u0430\"," +
								"\"type\": \"group\"," +
								"\"all_members_are_administrators\":false" +
								"}," +
								"\"date\": 1586690677," +
								"}" +
								"}",
						"There is no 'text' block in object."),
				Arguments.of("{" +
								"\"update_id\": 641170930," +
								"\"message\": {" +
								"\"message_id\": 785," +
								"\"from\": {" +
								"\"id\": 652216725," +
								"\"is_bot\": false," +
								"\"first_name\": \"Stas\"," +
								"\"last_name\": \"Pasynkov\"," +
								"\"username\": \"spasynkov\"," +
								"\"language_code\": \"uk\"" +
								"}," +
								"\"chat\": {" +
								"\"id\": -485469147," +
								"\"title\":\"\\u0420\\u0430\\u0437\\u0440\\u0430\\u0431\\u043e\\u0442\\u043a\\u0430 \\u0442\\u0435\\u043b\\u0435\\u0433\\u0440\\u0430\\u043c-\\u0431\\u043e\\u0442\\u0430\"," +
								"\"type\": \"group\"," +
								"\"all_members_are_administrators\":false" +
								"}," +
								"\"date\": 1586690677," +
								"\"text\": " + null +
								"}" +
								"}",
						"The 'text' object cannot be null.")
		);
	}
}
