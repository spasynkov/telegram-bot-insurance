package com.example.telegrambotinsurance.keyboards;

import com.example.telegrambotinsurance.exception.ButtonFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractKeyboard implements Keyboard{
	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractKeyboard.class);

	/**
	 * Лист листов кнопок
	 */
	protected List<List<Button>> keyboard = new ArrayList<>();

	/**
	 * Метод добавляет один ряд кнопок
	 */
	public void addKeyboardRow(){
		keyboard.add(new ArrayList<>());
	}

	/**
	 * Метод добавляет одну кнопку с переданным текстом
	 *  в ряд под переданным числом и в позицию под переданным числом
	 *  (отсчет начинается от 0)
	 *
	 * @param positionInTheRow Позиция кнопки в ряду
	 * @param rowNumber Позиция ряда в List
	 * @param button Кнопка
	 */
	public void addButton(int positionInTheRow, int rowNumber,Button button){
		try {
			keyboard.get(rowNumber).add(positionInTheRow,button);
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
			keyboard.get(keyboard.size()-1).add(positionInTheRow,button);
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
	public void addButton(Button button) {
		try {
			keyboard.get(keyboard.size()-1).add(button);
		}
		catch (ArrayIndexOutOfBoundsException e){
			LOGGER.debug("Row under this number doesn't exist");
		}
		catch (ButtonFormatException e){
			LOGGER.debug("Wrong button format");
		}
	}
}
