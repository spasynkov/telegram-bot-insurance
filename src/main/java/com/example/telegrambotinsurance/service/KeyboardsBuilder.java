package com.example.telegrambotinsurance.service;

import com.example.telegrambotinsurance.keyboards.*;
import lombok.Data;
import org.json.JSONObject;

@Data
public class KeyboardsBuilder {

	public static KeyboardBuilder builder(){
		return new KeyboardBuilder();
	}

	public static class KeyboardBuilder{
		private AbstractKeyboard keyboard;

		/**
		 * Создание reply клавиатуры без параметров
		 */
		public KeyboardBuilder builderReplyKeyboard(ReplyKeyboardMarkup keyboard){
			this.keyboard = keyboard;

			return this;
		}

		/**
		 * Создание inline клавиатуры
		 */
		public KeyboardBuilder builderInlineKeyboard(InlineKeyboardMarkup keyboard){
			this.keyboard = keyboard;

			return this;
		}

		/**
		 * Метод добавляет один ряд кнопок
		 */
		public KeyboardBuilder addRow(){
			keyboard.addKeyboardRow();

			return this;
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
		public KeyboardBuilder addButton(int positionInTheRow, int rowNumber, Button button){
			keyboard.addButton(positionInTheRow,rowNumber,button);

			return this;
		}

		/**
		 * Метод добавляет переданную кнопку в последний ряд
		 *  в позицию под переданным числом (отсчет начинается от 0)
		 *
		 * @param positionInTheRow Позиция кнопки в ряду
		 * @param button Кнопка
		 */
		public KeyboardBuilder addButton(int positionInTheRow,Button button){
			keyboard.addButton(positionInTheRow,button);

			return this;
		}

		/**
		 * Метод добавляет переданную кнопку в последний ряд
		 *  в последнию позицию в ряде
		 *
		 * @param button Кнопка
		 */
		public KeyboardBuilder addButton(Button button) {
			keyboard.addButton(button);

			return this;
		}

		/**
		 * Возращает клавиатуру в JSONObject виде
		 *
		 * @return JSONObject
		 */
		public JSONObject build(){
			return keyboard.toJson();
		}


		/**
		 * Возращает объект удаления клавиатуры
		 *
		 * @param keyboardRemove
		 * @return
		 */
		public JSONObject buildRemoveKeyboard(ReplyKeyboardRemove keyboardRemove){
			return keyboardRemove.toJson();
		}
	}
}
