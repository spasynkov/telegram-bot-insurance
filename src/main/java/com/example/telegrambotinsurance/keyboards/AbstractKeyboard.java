package com.example.telegrambotinsurance.keyboards;

import lombok.Getter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstractKeyboard implements Markup {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractKeyboard.class);

	/**
	 * Лист листов кнопок
	 */
	protected List<List<Button>> keyboard = new ArrayList<>();

	public JSONObject toJson() {
		return new JSONObject(this);
	}

 	public static abstract class AbstractKeyboardBuilder<T> {
		protected List<List<Button>> keyboard = new ArrayList<>();

		/**
		 * Метод добавляет один ряд кнопок
		 */
		public AbstractKeyboard.AbstractKeyboardBuilder<T> addRow() {
			keyboard.add(new ArrayList<>());

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
		public AbstractKeyboard.AbstractKeyboardBuilder<T> addButton(int rowNumber, int positionInTheRow, Button button) {
			try {
				keyboard.get(rowNumber).add(positionInTheRow,button);
			}
			catch (IndexOutOfBoundsException e){
				LOGGER.debug("Row under this number doesn't exist");
			}

			return this;
		}

		/**
		 * Метод добавляет переданную кнопку в последний ряд
		 *  в позицию под переданным числом (отсчет начинается от 0)
		 *
		 * @param positionInTheRow Позиция кнопки в ряду
		 * @param button Кнопка
		 */
		public AbstractKeyboard.AbstractKeyboardBuilder<T> addButton(int positionInTheRow,Button button) {
			int rowNumber = keyboard.size()-1;

			addButton(rowNumber,positionInTheRow,button);

			return this;
		}

		/**
		 * Метод добавляет переданную кнопку в последний ряд
		 *  в последнию позицию в ряде
		 *
		 * @param button Кнопка
		 */
		public AbstractKeyboard.AbstractKeyboardBuilder<T> addButton(Button button) {
			int positionInTheRow = keyboard.get(keyboard.size()-1).size();

			addButton(positionInTheRow,button);

			return this;
		}

	    /**
	     * Возращает собиранный объект
	     *
		 * @return T
		 */
		public abstract T build();
	}
}
