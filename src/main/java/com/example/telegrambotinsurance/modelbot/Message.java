package com.example.telegrambotinsurance.modelbot;

import java.util.Date;

public class Message {
	private long message_id;
	private Date date;
	private String text;

	public Message() {

	}

	public Message(long message_id, Date date, String text) {
		this.message_id = message_id;
		this.date = date;
		this.text = text;
	}

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString() {
		return "{message_id: "+ message_id + ", date: " + date + ", text: " + text + "}";
	}
}
