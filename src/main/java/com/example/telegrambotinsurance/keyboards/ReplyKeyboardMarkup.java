package com.example.telegrambotinsurance.keyboards;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Встроенная клавиатура
 */
@NoArgsConstructor
@Getter
public class ReplyKeyboardMarkup extends AbstractKeyboard{

	public List<List<Button>> getKeyboard(){
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

	public ReplyKeyboardMarkup(Boolean resize_keyboard, Boolean one_time_keyboard, Boolean selective) {
		this.resize_keyboard = resize_keyboard;
		this.one_time_keyboard = one_time_keyboard;
		this.selective = selective;
	}
}
