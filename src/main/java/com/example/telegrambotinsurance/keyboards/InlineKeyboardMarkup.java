package com.example.telegrambotinsurance.keyboards;

import com.example.telegrambotinsurance.exception.ButtonFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboardMarkup implements Keyboard{

	private static final Logger LOGGER = LoggerFactory.getLogger(InlineKeyboardMarkup.class);

	public List<List<InlineKeyboardButton>> getInline_keyboard() {
		return inline_keyboard;
	}

	/**
	 * Лист листов кнопок
	 */
	private List<List<InlineKeyboardButton>> inline_keyboard = new ArrayList<>();

	/**
	 * Метод добавляет один ряд кнопок
	 */
	public void addKeyboardRow(){
		inline_keyboard.add(new ArrayList<>());
	}

	/**
	 * Метод добавляет одну кнопку с переданным текстом
	 *  в ряд под переданным числом и в позицию под переданным числом
	 *  (отсчет начинается от 0)
	 *
	 * @param positionInTheRow Позиция кнопки в ряду
	 * @param rowPosition Позиция ряда в List
	 * @param button Кнопка
	 */
	public void addButton(int positionInTheRow, int rowPosition,Button button){
		try {
			inline_keyboard.get(rowPosition).add(positionInTheRow,(InlineKeyboardButton) button);
		}
		catch (ArrayIndexOutOfBoundsException e){
			LOGGER.debug("Row under this number doesn't exist");
		}
		catch (ButtonFormatException e){
			LOGGER.debug("Wrong button format");
		}
	}

	/**
	 * Метод добавляет переданную кнопку в последний ряд
	 *  в позицию под переданным числом (отсчет начинается от 0)
	 *
	 * @param positionInTheRow Позиция кнопки в ряду
	 * @param button Кнопка
	 */
	public void addButton(int positionInTheRow,Button button){
		try {
			inline_keyboard.get(inline_keyboard.size()-1).add(positionInTheRow,(InlineKeyboardButton) button);
		}
		catch (ArrayIndexOutOfBoundsException e){
			LOGGER.debug("Row under this number doesn't exist");
		}
		catch (ButtonFormatException e){
			LOGGER.debug("Wrong button format");
		}
	}

	/**
	 * Метод добавляет переданную кнопку в последний ряд
	 *  в последнию позицию в ряде
	 *
	 * @param button Кнопка
	 */
	@Override
	public void addButton(Button button) {
		try {
			inline_keyboard.get(inline_keyboard.size()-1).add((InlineKeyboardButton) button);
		}
		catch (ArrayIndexOutOfBoundsException e){
			LOGGER.debug("Row under this number doesn't exist");
		}
		catch (ButtonFormatException e){
			LOGGER.debug("Wrong button format");
		}
	}
}
