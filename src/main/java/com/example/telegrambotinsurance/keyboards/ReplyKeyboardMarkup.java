package com.example.telegrambotinsurance.keyboards;

import java.util.List;

/**
 * Встроенная клавиатура
 */
public class ReplyKeyboardMarkup extends AbstractKeyboard {

	private ReplyKeyboardMarkup(List<List<Button>> keyboard, Boolean resize_keyboard, Boolean one_time_keyboard, Boolean selective) {
		this.keyboard = keyboard;
		this.resize_keyboard = resize_keyboard;
		this.one_time_keyboard = one_time_keyboard;
		this.selective = selective;
	}

	public List<List<Button>> getKeyboard() {
		return keyboard;
	}

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Подгоняет размер клавиатуры под количество рядов кнопок
	 * (если false, то клавиатура будет как стандартная у пользователя(на телефоне))
	 */
	private Boolean resize_keyboard = false;
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Если true, то после использования клавиатура скроется,
	 * но её можно будет открыть с помощью специальной кнопки
	 */
	private Boolean one_time_keyboard = false;
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Показывает определенную клавиатуру определенному пользователю
	 * Пример:
	 *  Пользователь запрашивает изменение языка бота,
	 *  бот отвечает на запрос с помощью клавиатуры, чтобы выбрать новый язык.
	 *  Другие пользователи в группе не видят клавиатуру, который выбрал пользователь.
	 */
	private Boolean selective = null;

	public static ReplyKeyboardMarkup.ReplyKeyboardBuilder builder() {
		return new ReplyKeyboardMarkup.ReplyKeyboardBuilder();
	}

	public static class ReplyKeyboardBuilder extends AbstractKeyboard.AbstractKeyboardBuilder<ReplyKeyboardMarkup> {
		private Boolean resize_keyboard = false;

		private Boolean one_time_keyboard = false;

		private Boolean selective = null;

		public ReplyKeyboardBuilder setResize_keyboard(Boolean resize_keyboard) {
			this.resize_keyboard = resize_keyboard;

			return this;
		}

		public ReplyKeyboardBuilder setOne_time_keyboard(Boolean one_time_keyboard) {
			this.one_time_keyboard = one_time_keyboard;

			return this;
		}

		public ReplyKeyboardBuilder setSelective(Boolean selective) {
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
			return new ReplyKeyboardMarkup(keyboard,resize_keyboard,one_time_keyboard,selective);
		}
	}
}
