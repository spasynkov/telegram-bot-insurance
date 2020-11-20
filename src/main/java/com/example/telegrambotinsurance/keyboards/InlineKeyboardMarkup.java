package com.example.telegrambotinsurance.keyboards;

import com.example.telegrambotinsurance.exception.ButtonFormatException;
import com.example.telegrambotinsurance.exception.MethodNotSupported;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardMarkup implements Keyboard{

	private static final Logger LOGGER = LoggerFactory.getLogger(Keyboard.class);

	public List<List<InlineKeyboardButton>> getInline_keyboard() {
		return inline_keyboard;
	}

	/**
	 * Массив массивов кнопок
	 */
	private List<List<InlineKeyboardButton>> inline_keyboard = new ArrayList<>();

	/**
	 * Метод добавляет один ряд кнопок
	 */
	public void addKeyboardRow(){
		inline_keyboard.add(new ArrayList<>());
	}

	/**
	 * Method not supported
	 */
	public void addButton(int number,String textButton){
		try {
			throw new MethodNotSupported();
		} catch (MethodNotSupported methodNotSupported) {
			LOGGER.debug("Method not supported");
		}
	}

	/**
	 * Метод добавляет переданную кноку в ряд под переданным числом (отсчет начинается от 0)
	 * @param number
	 * @param button
	 */
	public void addButton(int number, Button button){
		try {
			inline_keyboard.get(number).add((InlineKeyboardButton) button);
		}
		catch (ArrayIndexOutOfBoundsException e){
			LOGGER.debug("Row under this number doesn't exist");
		}
		catch (ButtonFormatException e){
			LOGGER.debug("Wrong button format");
		}
	}
}
