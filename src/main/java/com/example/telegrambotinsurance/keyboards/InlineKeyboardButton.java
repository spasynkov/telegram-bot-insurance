package com.example.telegrambotinsurance.keyboards;

import lombok.Builder;
import lombok.Data;
import org.json.JSONPropertyName;

@Data
@Builder
public class InlineKeyboardButton implements Button{
	/**
	 * ВНИМАНИЕ
	 * Вы должны использовать ровно одно из необязательных полей.
	 */

	/**
	 * Текст кнопки
	 */
	private String text;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * url callback/webhook, который открывается при нажатии кнопки
	 */
	private String url;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * url callback/webhook, используемый для автоматической авторизации пользователя
	 * на сайтах под акккаунтом телеграма(например сайт comments.app)
	 */
	private LoginUrl loginUrl;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Данные для отправки боту в ответном запросе при нажатии кнопки, 1-64 байта
	 * НАПРИМЕР
	 *  Бот отправил сообщение "Привет" с Inline клавиатурой,
	 *  то при нажатии кнопки, сообщение изменится на "Пока"
	 *  И ещё много чего можно сделать
	 */
	private String callbackData;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Если установлено, нажатие кнопки предложит пользователю выбрать один из своих чатов,
	 * затем открыть этот чат и вставить имя бота и указанный встроенный запрос в поле ввода.
	 * Может быть пустым, и в этом случае будет вставлено только имя бота.
	 *
	 * Примечание.
	 *  Это дает пользователям простой способ начать использовать вашего бота во встроенном режиме,
	 *  когда они в данный момент находятся в приватном чате с ним.
	 *  Особенно полезно в сочетании с действиями switch_pm…(https://core.telegram.org/bots/api#answerinlinequery)
	 *  - в этом случае пользователь автоматически вернется в чат,
	 *  из которого он переключился, пропуская экран выбора чата.
	 */
	private String switchInlineQuery;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Такая же переменная как switch_inline_query,
	 * только работает во всех чатах
	 *
	 * НАПРИМЕР
	 *  Если бот подбирает похожие смайлики,
	 *  то с помощью встроенного запроса не надо заходить в приватный чат к боту,
	 *  а сразу напрямую в любом чате(пример такого бота https://telegram.me/sticker)
	 *
	 * Это предлагает пользователю быстрый способ открыть вашего бота во встроенном режиме в том же чате
	 * - удобно для выбора из нескольких вариантов.
	 */
	private String switchInlineQueryCurrentChat;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Описание игры, которая будет запускаться при нажатии пользователем кнопки.
	 *
	 * ПРИМЕЧАНИЕ.
	 *  Кнопка этого типа всегда ДОЛЖНА быть первой в первом ряду кнопок.
	 */
	private CallbackGame callbackGame;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Укажите True, чтобы отправить кнопку Pay(https://core.telegram.org/bots/api#payments).
	 * Кнопка Pay нужна для оплаты
	 *
	 * ПРИМЕЧАНИЕ.
	 *  Кнопка этого типа всегда ДОЛЖНА быть первой в первом ряду кнопок.
	 */
	private Boolean pay;

	@JSONPropertyName("login_url")
	public LoginUrl getLoginUrl() {
		return loginUrl;
	}

	@JSONPropertyName("callback_data")
	public String getCallbackData() {
		return callbackData;
	}

	@JSONPropertyName("switch_inline_query")
	public String getSwitchInlineQuery() {
		return switchInlineQuery;
	}

	@JSONPropertyName("switch_inline_query_current_chat")
	public String getSwitchInlineQueryCurrentChat() {
		return switchInlineQueryCurrentChat;
	}

	@JSONPropertyName("callback_game")
	public CallbackGame getCallbackGame() {
		return callbackGame;
	}

	private InlineKeyboardButton(String text, String url, LoginUrl loginUrl,
	                             String callbackData, String switchInlineQuery,
	                             String switchInlineQueryCurrentChat, CallbackGame callbackGame, Boolean pay) {
		this.text = text;
		this.url = url;
		this.loginUrl = loginUrl;
		this.callbackData = callbackData;
		this.switchInlineQuery = switchInlineQuery;
		this.switchInlineQueryCurrentChat = switchInlineQueryCurrentChat;
		this.callbackGame = callbackGame;
		this.pay = pay;
	}
	/*
	В этом классе переопределяются нужный нам метод из lombok builder
	 */
	public static class InlineKeyboardButtonBuilder {

		public InlineKeyboardButton build() {
			verifyButtonFormat();

			return new InlineKeyboardButton(text, url, loginUrl, callbackData,
					switchInlineQuery, switchInlineQueryCurrentChat,
					callbackGame, pay);
		}

		/**
		 * Проверка кнопки под формат API Telegram
		 */
		private void verifyButtonFormat() {
			if (text == null || text.isEmpty()) {
				throw new IllegalStateException("The button must contain text.");
			}
			int numberOfFieldsSet = 0;
			if (url != null) ++numberOfFieldsSet;
			if (loginUrl != null) ++numberOfFieldsSet;
			if (callbackData != null) ++numberOfFieldsSet;
			if (switchInlineQuery != null) ++numberOfFieldsSet;
			if (switchInlineQueryCurrentChat != null) ++numberOfFieldsSet;
			if (callbackGame != null) ++numberOfFieldsSet;
			if (pay != null) ++numberOfFieldsSet;
			if (numberOfFieldsSet != 1)
				throw new IllegalStateException("The button should have only one optional field.");
		}
	}
}
