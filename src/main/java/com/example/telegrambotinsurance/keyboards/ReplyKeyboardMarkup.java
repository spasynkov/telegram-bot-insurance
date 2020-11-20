package com.example.telegrambotinsurance.keyboards;


import com.example.telegrambotinsurance.exception.ButtonFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardMarkup implements Keyboard{

	private static final Logger LOGGER = LoggerFactory.getLogger(Keyboard.class);
	/**
	 * Массив массивов кнопок
	 */
	private List<List<KeyboardButton>> keyboard = new ArrayList<>();

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Подгоняет ращмер клавиатуры под количество рядов
	 * (если false, то клавиатура будет как стандартная у пользователя)
	 */
	private Boolean resize_keyboard = false;
	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Если true, то после ипользования клавиатура скроется,
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

	public ReplyKeyboardMarkup(Boolean resize_keyboard) {
		this.resize_keyboard = resize_keyboard;
	}

	public ReplyKeyboardMarkup(Boolean resize_keyboard, Boolean one_time_keyboard) {
		this.resize_keyboard = resize_keyboard;
		this.one_time_keyboard = one_time_keyboard;
	}

	public ReplyKeyboardMarkup(Boolean resize_keyboard, Boolean one_time_keyboard, Boolean selective) {
		this.resize_keyboard = resize_keyboard;
		this.one_time_keyboard = one_time_keyboard;
		this.selective = selective;
	}

	/**
	 * Метод добавляет один ряд кнопок
	 */
	public void addKeyboardRow(){
		keyboard.add(new ArrayList<>());
	}

	/**
	 * Метод добавляет одну кноку с переданным текстом в ряд под переданным числом
	 * @param number
	 * @param textButton
	 */
	public void addButton(int number,String textButton){
		try {
			keyboard.get(number).add(new KeyboardButton(textButton));
		}
		catch (ArrayIndexOutOfBoundsException e){
			LOGGER.debug("Row under this number doesn't exist");
		}
	}

	/**
	 * Метод добавляет переданную кноку в ряд под переданным числом (отсчет начинается от 0)
	 * @param number
	 * @param button
	 */
	public void addButton(int number,Button button){
		try {
			keyboard.get(number).add((KeyboardButton) button);
		}
		catch (ArrayIndexOutOfBoundsException e){
			LOGGER.debug("Row under this number doesn't exist");
		}
		catch (ButtonFormatException e){
			LOGGER.debug("Wrong button format");
		}
	}

	public Boolean getResize_keyboard() {
		return resize_keyboard;
	}

	public void setResize_keyboard(Boolean resize_keyboard) {
		this.resize_keyboard = resize_keyboard;
	}

	public Boolean getOne_time_keyboard() {
		return one_time_keyboard;
	}

	public void setOne_time_keyboard(Boolean one_time_keyboard) {
		this.one_time_keyboard = one_time_keyboard;
	}

	public Boolean getSelective() {
		return selective;
	}

	public void setSelective(Boolean selective) {
		this.selective = selective;
	}

	public List<List<KeyboardButton>> getKeyboard() {
		return keyboard;
	}
}
