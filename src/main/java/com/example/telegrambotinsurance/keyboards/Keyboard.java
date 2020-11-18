package com.example.telegrambotinsurance.keyboards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public interface Keyboard {

	/**
	 * Метод добавляет один ряд кнопок
	 */
	void addKeyboardRow();

	/**
	 * Метод добавляет одну кноку с переданным текстом в ряд под переданным числом (отсчет начинается от 0)
	 * @param number
	 * @param textButton
	 */
	void addButton(int number,String textButton);

	/**
	 * Метод добавляет переданную кноку в ряд под переданным числом (отсчет начинается от 0)
	 * @param number
	 * @param button
	 */
	void addButton(int number,Button button);
}
