package com.example.telegrambotinsurance.keyboards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@Getter
public class ReplyKeyboardButton implements Button{
	private static final Logger LOGGER = LoggerFactory.getLogger(ReplyKeyboardButton.class);
	/**
	 * Текст кнопки
	 */
	private String text;
	/**
	 * Следущие две переменные взаимоисключающии
	 */
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Предлагает пользователю отправить свой номер телефона
	 */
	private Boolean request_contact = false;
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Предлагает пользователю отправить свою геолокацию
	 */
	private Boolean request_location = false;

	public ReplyKeyboardButton(String text) {
		this.text = text;
	}
}

