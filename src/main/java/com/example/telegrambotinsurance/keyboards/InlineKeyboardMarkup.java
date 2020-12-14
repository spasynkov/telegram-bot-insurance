package com.example.telegrambotinsurance.keyboards;

import lombok.NoArgsConstructor;
import org.json.JSONPropertyName;

import java.util.List;

/**
 * Клавиатура, которая прикрепляется к сообщению
 */
@NoArgsConstructor
public class InlineKeyboardMarkup extends AbstractKeyboard {

	private InlineKeyboardMarkup(List<List<Button>> keyboard) {
		this.keyboard = keyboard;
	}

	@JSONPropertyName("inline_keyboard")
	public List<List<Button>> getKeyboard() {
		return keyboard;
	}

	public static InlineKeyboardMarkup.InlineKeyboardBuilder builder() {
		return new InlineKeyboardBuilder();
	}

	public static class InlineKeyboardBuilder extends AbstractKeyboard.AbstractKeyboardBuilder<InlineKeyboardMarkup> {

		/**
		 * Возращает собранный объект
		 *
		 * @return InlineKeyboardMarkup
		 */
		@Override
		public InlineKeyboardMarkup build() {
			return new InlineKeyboardMarkup(keyboard);
		}
	}
}
