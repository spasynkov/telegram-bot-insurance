package com.example.telegrambotinsurance.modelbot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	private int chatId;
	private int messageId;
	private Date date;
	private String text;
}
