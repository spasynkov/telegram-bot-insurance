package com.example.telegrambotinsurance.keyboards;


import com.example.telegrambotinsurance.exception.ButtonFormatException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ReplyKeyboardMarkup extends AbstractKeyboard{

	/**
	 * Лист листов кнопок
	 */
	private List<List<ReplyKeyboardButton>> keyboard = new ArrayList<>();

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
	 * @param rowPosition Позиция ряда в List
	 * @param button Кнопка
	 */
	public void addButton(int positionInTheRow, int rowPosition,Button button){
		try {
			keyboard.get(rowPosition).add(positionInTheRow,(ReplyKeyboardButton) button);
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
			keyboard.get(keyboard.size()-1).add(positionInTheRow,(ReplyKeyboardButton) button);
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
			keyboard.get(keyboard.size()-1).add((ReplyKeyboardButton) button);
		}
		catch (ArrayIndexOutOfBoundsException e){
			LOGGER.debug("Row under this number doesn't exist");
		}
		catch (ButtonFormatException e){
			LOGGER.debug("Wrong button format");
		}
	}
}
