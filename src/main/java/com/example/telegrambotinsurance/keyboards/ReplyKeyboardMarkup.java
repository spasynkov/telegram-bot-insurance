package com.example.telegrambotinsurance.keyboards;

import java.util.List;

import org.json.JSONPropertyName;

import lombok.Getter;

/**
 * Встроенная клавиатура
 */
@Getter
public class ReplyKeyboardMarkup extends AbstractKeyboard {

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Подгоняет размер клавиатуры под количество рядов кнопок
	 * (если false, то клавиатура будет как стандартная у пользователя(на телефоне))
	 */
	@Getter(onMethod_ = {@JSONPropertyName("resize_keyboard")})
	private final Boolean resizeKeyboard;
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Если true, то после использования клавиатура скроется,
	 * но её можно будет открыть с помощью специальной кнопки
	 */
	@Getter(onMethod_ = {@JSONPropertyName("one_time_keyboard")})
	private final Boolean oneTimeKeyboard;
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Показывает определенную клавиатуру определенному пользователю
	 * Пример:
	 * Пользователь запрашивает изменение языка бота,
	 * бот отвечает на запрос с помощью клавиатуры, чтобы выбрать новый язык.
	 * Другие пользователи в группе не видят клавиатуру, который выбрал пользователь.
	 */
	private final Boolean selective;

	private ReplyKeyboardMarkup(List<List<Button>> keyboard, Boolean resizeKeyboard, Boolean oneTimeKeyboard, Boolean selective) {
		this.keyboard = keyboard;
		this.resizeKeyboard = resizeKeyboard;
		this.oneTimeKeyboard = oneTimeKeyboard;
		this.selective = selective;
	}

	public static ReplyKeyboardMarkup.ReplyKeyboardBuilder builder() {
		return new ReplyKeyboardMarkup.ReplyKeyboardBuilder();
	}

	public static class ReplyKeyboardBuilder extends AbstractKeyboard.AbstractKeyboardBuilder<ReplyKeyboardMarkup> {

		private Boolean resizeKeyboard = null;

		private Boolean oneTimeKeyboard = null;

		private Boolean selective = null;

		public ReplyKeyboardBuilder setResizeKeyboard(boolean resizeKeyboard) {
			this.resizeKeyboard = resizeKeyboard;

			return this;
		}

		public ReplyKeyboardBuilder setOneTimeKeyboard(boolean oneTimeKeyboard) {
			this.oneTimeKeyboard = oneTimeKeyboard;

			return this;
		}

		public ReplyKeyboardBuilder setSelective(boolean selective) {
			this.selective = selective;

			return this;
		}

		/**
		 * Возращает собранный объект
		 *
		 * @return ReplyKeyboardMarkup
		 */
		@Override
		public ReplyKeyboardMarkup build() {
			return new ReplyKeyboardMarkup(keyboard, resizeKeyboard, oneTimeKeyboard, selective);
		}
	}
}
