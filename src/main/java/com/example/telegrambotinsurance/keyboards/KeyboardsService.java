package com.example.telegrambotinsurance.keyboards;

import com.example.telegrambotinsurance.exception.ButtonFormatException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class KeyboardsService {
	private Keyboard keyboard;

	/**
	 * Создание reply клавиатуры без параметров
	 */
	public void createReplyKeyboard(){
		keyboard = new ReplyKeyboardMarkup();
	}

	/**
	 * Создание reply клавиатуры с параметрами
	 *
	 * @param resize_keyboard
	 * @param one_time_keyboard
	 * @param selective
	 */
	public void createReplyKeyboard(Boolean resize_keyboard, Boolean one_time_keyboard, Boolean selective){
		keyboard = new ReplyKeyboardMarkup(resize_keyboard,one_time_keyboard,selective);
	}

	/**
	 * Создание inline клавиатуры
	 */
	public void createInlineKeyboard(){
		keyboard = new InlineKeyboardMarkup();
	}

	/**
	 * Метод добавляет один ряд кнопок
	 */
	public void addRow(){
		keyboard.addKeyboardRow();
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
		keyboard.addButton(positionInTheRow,rowPosition,button);
	}

	/**
	 * Метод добавляет переданную кнопку в последний ряд
	 *  в позицию под переданным числом (отсчет начинается от 0)
	 *
	 * @param positionInTheRow Позиция кнопки в ряду
	 * @param button Кнопка
	 */
	public void addButton(int positionInTheRow,Button button){
		keyboard.addButton(positionInTheRow,button);
	}

	/**
	 * Метод добавляет переданную кнопку в последний ряд
	 *  в последнию позицию в ряде
	 *
	 * @param button Кнопка
	 */
	public void addButton(Button button) {
		keyboard.addButton(button);
	}

	/**
	 * Возращает клавиатуру в JSONObject виде
	 *
	 * @return JSONObject
	 */
	public JSONObject getKeyboard(){
		return new JSONObject(keyboard);
	}

	/**
	 * Возращает объект удаления клавиатуры
	 *
	 * @return JSONObject
	 */
	public JSONObject removeKeyboard(){
		ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
		return new JSONObject(replyKeyboardRemove);
	}

	/**
	 * Возращает объект удаления клавиатуры с параметром selective
	 *
	 * @param selective
	 * @return JSONObject
	 */
	public JSONObject removeKeyboard(Boolean selective){
		ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove(selective);
		return new JSONObject(replyKeyboardRemove);
	}
}
