package com.example.telegrambotinsurance.keyboards;

public class KeyboardButton implements Button{
	private String text;
	private Boolean request_contact = false;
	private Boolean request_location = false;

	public String getText() {
		return text;
	}

	public Boolean getRequest_contact() {
		return request_contact;
	}

	public Boolean getRequest_location() {
		return request_location;
	}

	public KeyboardButton(String text) {
		this.text = text;
	}

	public KeyboardButton(String text, Boolean request_contact) {
		this.text = text;
		this.request_contact = request_contact;
	}

	public KeyboardButton(String text, Boolean request_contact, Boolean request_location) {
		this.text = text;
		this.request_contact = request_contact;
		this.request_location = request_location;
	}
}

