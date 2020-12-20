package com.example.telegrambotinsurance.keyboards;

import lombok.Builder;
import lombok.Getter;
import org.json.JSONPropertyName;
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
	private Boolean requestContact = false;
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Предлагает пользователю отправить свою геолокацию
	 */
	private Boolean requestLocation = false;

	@JSONPropertyName("request_contact")
	public Boolean getRequestContact() {
		return requestContact;
	}

	@JSONPropertyName("request_location")
	public Boolean getRequestLocation() {
		return requestLocation;
	}

	public ReplyKeyboardButton(String text) {
		this.text = text;
	}

	private ReplyKeyboardButton(String text, Boolean requestContact, Boolean requestLocation) {
		this.text = text;
		this.requestContact = requestContact;
		this.requestLocation = requestLocation;
	}

	/*
	В этом классе переопределяются нужный нам метод из lombok builder
	 */
	public static class ReplyKeyboardButtonBuilder {
		private String text;
		private Boolean requestContact = false;
		private Boolean requestLocation = false;

		public ReplyKeyboardButton build() {
			verifyButtonFormat();

			return new ReplyKeyboardButton(text, requestContact, requestLocation);
		}

		/**
		 * Проверка кнопки под формат API Telegram
		 */
		private void verifyButtonFormat() {
			if (text == null || text.isEmpty()) {
				throw new IllegalStateException("The button must contain text.");
			}
			if (requestContact && requestLocation) {
				throw new IllegalStateException("Cannot send 'request_contact' and 'request_location'.");
			}
		}
	}
}

