package com.example.telegrambotinsurance.keyboards;

public class InlineKeyboardButton implements InlineButtonInterface{
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
	 * HTTP или tg: // url, который открывается при нажатии кнопки
	 */
	private String url;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * URL-адрес HTTP, используемый для автоматической авторизации пользователя.
	 */
	private String login_url;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Данные для отправки боту в ответном запросе при нажатии кнопки, 1-64 байта
	 */
	private String callback_data;

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
	private String switch_inline_query;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Если установлено, нажатие кнопки вставит имя бота
	 * и указанный встроенный запрос в поле ввода текущего чата.
	 * Может быть пустым, и в этом случае будет вставлено только имя бота.
	 *
	 * Это предлагает пользователю быстрый способ открыть вашего бота во встроенном режиме в том же чате
	 * - удобно для выбора из нескольких вариантов.
	 */
	private String switch_inline_query_current_chat;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Описание игры, которая будет запускаться при нажатии пользователем кнопки.
	 *
	 * ПРИМЕЧАНИЕ.
	 *  Кнопка этого типа всегда ДОЛЖНА быть первой в ряду.
	 */
	private CallbackGame callback_game;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Укажите True, чтобы отправить кнопку Pay.
	 *
	 * ПРИМЕЧАНИЕ.
	 *  Кнопка этого типа всегда ДОЛЖНА быть первой в ряду.
	 */
	private Boolean pay;

	protected InlineKeyboardButton(){

	}

	public void setText(String text) {
		this.text = text;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setLogin_url(String login_url) {
		this.login_url = login_url;
	}

	public void setCallback_data(String callback_data) {
		this.callback_data = callback_data;
	}

	public void setSwitch_inline_query(String switch_inline_query) {
		this.switch_inline_query = switch_inline_query;
	}

	public void setSwitch_inline_query_current_chat(String switch_inline_query_current_chat) {
		this.switch_inline_query_current_chat = switch_inline_query_current_chat;
	}

	public void setCallback_game(CallbackGame callback_game) {
		this.callback_game = callback_game;
	}

	public void setPay(Boolean pay) {
		this.pay = pay;
	}

	public String getText() {
		return text;
	}

	public String getUrl() {
		return url;
	}

	public String getLogin_url() {
		return login_url;
	}

	public String getCallback_data() {
		return callback_data;
	}

	public String getSwitch_inline_query() {
		return switch_inline_query;
	}

	public String getSwitch_inline_query_current_chat() {
		return switch_inline_query_current_chat;
	}

	public CallbackGame getCallback_game() {
		return callback_game;
	}

	public Boolean getPay() {
		return pay;
	}

	public interface Builder{
		InlineKeyboardButton build();

		InlineKeyboardButtonBuilder setText(String text);

		InlineKeyboardButtonBuilder setUrl(String url);

		InlineKeyboardButtonBuilder setLogin_url(String login_url);

		InlineKeyboardButtonBuilder setCallback_data(String callback_data);

		InlineKeyboardButtonBuilder setSwitch_inline_query(String switch_inline_query);

		InlineKeyboardButtonBuilder setSwitch_inline_query_current_chat(String switch_inline_query_current_chat);

		InlineKeyboardButtonBuilder setCallback_game(CallbackGame callback_game);

		InlineKeyboardButtonBuilder setPay(Boolean pay);
	}
}
