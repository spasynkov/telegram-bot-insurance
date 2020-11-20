package com.example.telegrambotinsurance.keyboards;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class KeyboardsService {
	private Keyboard keyboard;


	/**
	 * @return Возращает стартовое меню в JSON виде
	 */
	public JSONObject getStarterMenu(){
		keyboard = new ReplyKeyboardMarkup(true,true);
		keyboard.addKeyboardRow();
		keyboard.addKeyboardRow();
		keyboard.addButton(0,"Оформить договор");
		keyboard.addButton(1,"Контакты");
		keyboard.addButton(1,"Оплата");
		keyboard.addButton(1,"Помощь");

		return new JSONObject(keyboard);
	}

	/**
	 * @return Возращает меню самых частых вопросов в JSON виде
	 */
	public JSONObject getFrequentQuestions(){
		keyboard = new ReplyKeyboardMarkup(true,true);
		keyboard.addKeyboardRow();
		keyboard.addKeyboardRow();
		keyboard.addButton(0,"Частые вопросы");
		keyboard.addButton(1,"1");
		keyboard.addButton(1,"2");
		keyboard.addButton(1,"3");

		return new JSONObject(keyboard);
	}

	/**
	 * @return Возращает меню оформления договора в JSON виде
	 */
	public JSONObject getExecutionOfTheContract(){
		keyboard = new ReplyKeyboardMarkup(true,true);
		keyboard.addKeyboardRow();
		keyboard.addButton(0,"Получить код");
		keyboard.addButton(0,"Ввести код");

		return new JSONObject(keyboard);
	}

	/**
	 * @return Возращает меню получения кода в JSON виде
	 */
	public JSONObject getTheCode(){
		keyboard = new ReplyKeyboardMarkup(true,true);
		keyboard.addKeyboardRow();
		keyboard.addButton(0,"Личная рекомендация");
		keyboard.addButton(0,"Реклама");

		return new JSONObject(keyboard);
	}

	/**
	 * @return Возращает меню отправки номера телефона в JSON виде
	 */
	public JSONObject getSendUserNumber(){
		keyboard = new ReplyKeyboardMarkup(true,true);
		keyboard.addKeyboardRow();
		keyboard.addButton(0, new KeyboardButton("Отправить свой номер телефона", true));

		return new JSONObject(keyboard);
	}

	/**
	 * Возращает строку, которая удаляет клавиатуру
	 * @return Возращает строку в JSON виде
	 */
	public String removeKeyboard(){
		return "{\"chat_id\":-1001484722738, \"text\":\"Test\", \"reply_markup\":{\"remove_keyboard\":true}}";
	}

	/**
	 * ЭТО НЕ ИЗ ТЗ, ПРОСТО ДЛЯ ТЕСТА
	 * Возращает строку, которая добавляет встроенную клавиатуру
	 * @return Возращает строку в JSON виде
	 */
	public JSONObject getTestInlineKeyboard(){
		keyboard = new InlineKeyboardMarkup();
		keyboard.addKeyboardRow();
		keyboard.addKeyboardRow();
		keyboard.addButton(0, InlineButtonInterface.builder()
				.setText("1").setUrl("https://core.telegram.org/bots/api#inlinekeyboardbutton").build());
		keyboard.addButton(1, InlineButtonInterface.builder()
				.setText("2").setUrl("https://core.telegram.org/bots/api#inlinekeyboardbutton").build());

		return new JSONObject(keyboard);
	}
}
