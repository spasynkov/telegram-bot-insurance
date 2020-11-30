package com.example.telegrambotinsurance.keyboards;

import org.json.JSONPropertyName;

import java.util.List;

/**
 * Клавиатура, которая прикрепляется к сообщению
 */
public class InlineKeyboardMarkup extends AbstractKeyboard{

	@JSONPropertyName("inline_keyboard")
	public List<List<Button>> getKeyboard(){
		return keyboard;
	}
}
