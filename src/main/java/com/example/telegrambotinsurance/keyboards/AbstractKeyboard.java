package com.example.telegrambotinsurance.keyboards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractKeyboard implements Keyboard{
	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractKeyboard.class);

	/**
	 * Метод добавляет один ряд кнопок
	 */
	public abstract void addKeyboardRow();

	/**
	 * Метод добавляет одну кнопку с переданным текстом
	 *  в ряд под переданным числом и в позицию под переданным числом
	 *  (отсчет начинается от 0)
	 *
	 * @param positionInTheRow Позиция кнопки в ряду
	 * @param rowPosition Позиция ряда в List
	 * @param button Кнопка
	 */
	public abstract void addButton(int positionInTheRow, int rowPosition, Button button);

	/**
	 * Метод добавляет переданную кнопку в последний ряд
	 *  в позицию под переданным числом (отсчет начинается от 0)
	 *
	 * @param positionInTheRow Позиция кнопки в ряду
	 * @param button Кнопка
	 */
	public abstract void addButton(int positionInTheRow, Button button);

	/**
	 * Метод добавляет переданную кнопку в последний ряд
	 *  в последнию позицию в ряде
	 *
	 * @param button Кнопка
	 */
	public abstract void addButton(Button button);
}
