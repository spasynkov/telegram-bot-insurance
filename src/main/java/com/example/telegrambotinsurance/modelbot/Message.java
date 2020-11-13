package com.example.telegrambotinsurance.modelbot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	private long message_id;
	private Date date;
	private String text;
}
