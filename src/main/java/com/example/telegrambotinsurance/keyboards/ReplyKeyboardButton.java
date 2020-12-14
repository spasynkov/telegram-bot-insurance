package com.example.telegrambotinsurance.keyboards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс без стандартного конструктора,
 * потому что кнопка не может быть без текста
 */
@Getter
@Builder
public class ReplyKeyboardButton implements Button {
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

	private ReplyKeyboardButton(String text, Boolean request_contact, Boolean request_location) {
		this.text = text;
		this.request_contact = request_contact;
		this.request_location = request_location;
	}

	public static class ReplyKeyboardButtonBuilder {
		private String text;
		private Boolean request_contact = false;
		private Boolean request_location = false;

		public ReplyKeyboardButton build() {
			verifyButtonFormat();

			return new ReplyKeyboardButton(text, request_contact, request_location);
		}

		private void verifyButtonFormat() {
			if (text == null || text.isEmpty()) {
				throw new IllegalStateException("The button must contain text.");
			}
			if (request_contact && request_location) {
				throw new IllegalStateException("Cannot send 'request_contact' and 'request_location'.");
			}
		}
	}
}

