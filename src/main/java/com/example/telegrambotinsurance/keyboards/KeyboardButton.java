package com.example.telegrambotinsurance.keyboards;

public class KeyboardButton implements Button{
	/**
	 * Текст кнопки
	 */
	private String text;
	/**
	 * Следущие две переменные взаимоисключающии
	 */
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Предлагает пользователю отпрвить свой номер телефона
	 */
	private Boolean request_contact = false;
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Предлагает пользователю отпрвить свою геолокацию
	 */
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

