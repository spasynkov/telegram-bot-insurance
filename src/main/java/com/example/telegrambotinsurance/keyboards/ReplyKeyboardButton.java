package com.example.telegrambotinsurance.keyboards;

import org.json.JSONPropertyName;

import lombok.Builder;
import lombok.Getter;

/**
 * Класс без стандартного конструктора,
 * потому что кнопка не может быть без текста
 */
@Getter
@Builder
public class ReplyKeyboardButton implements Button {
	/**
	 * Текст кнопки
	 */
	private final String text;
	/**
	 * Следущие две переменные взаимоисключающии
	 */
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Предлагает пользователю отправить свой номер телефона
	 */
	@Getter(onMethod_ = @JSONPropertyName("request_contact"))
	private Boolean requestContact;
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Предлагает пользователю отправить свою геолокацию
	 */
	@Getter(onMethod_ = @JSONPropertyName("request_location"))
	private Boolean requestLocation;

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
		private final Boolean requestContact = false;
		private final Boolean requestLocation = false;

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
				throw new IllegalStateException("Unable to set both 'request_contact' and 'request_location' at the same time.");
			}
		}
	}
}

