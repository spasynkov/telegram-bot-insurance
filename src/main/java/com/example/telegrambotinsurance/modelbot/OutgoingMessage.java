package com.example.telegrambotinsurance.modelbot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * this class is necessary for generating a response
 * from the telegram API and changing and storing the parameters
 * of this response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OutgoingMessage {

	private String stringChatId;
	private long longChatId;
	private String text;

}
