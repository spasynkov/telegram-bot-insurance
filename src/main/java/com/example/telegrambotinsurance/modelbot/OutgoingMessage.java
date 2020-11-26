package com.example.telegrambotinsurance.modelbot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OutgoingMessage {

	private String stringChatId;
	private long longChatId;
	private String text;

}
