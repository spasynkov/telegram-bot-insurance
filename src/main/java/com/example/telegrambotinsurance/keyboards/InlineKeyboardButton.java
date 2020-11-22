package com.example.telegrambotinsurance.keyboards;

import lombok.Builder;
import lombok.Data;

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
	private String login_url;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Данные для отправки боту в ответном запросе при нажатии кнопки, 1-64 байта
	 * НАПРИМЕР
	 *  Бот отправил сообщение "Привет" с Inline клавиатурой,
	 *  то при нажатии кнопки, сообщение изменится на "Пока"
	 *  И ещё много чего можно сделать
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
	private String switch_inline_query_current_chat;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Описание игры, которая будет запускаться при нажатии пользователем кнопки.
	 *
	 * ПРИМЕЧАНИЕ.
	 *  Кнопка этого типа всегда ДОЛЖНА быть первой в первом ряду кнопок.
	 */
	private CallbackGame callback_game;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Укажите True, чтобы отправить кнопку Pay(https://core.telegram.org/bots/api#payments).
	 * Кнопка Pay нужна для оплаты
	 *
	 * ПРИМЕЧАНИЕ.
	 *  Кнопка этого типа всегда ДОЛЖНА быть первой в первом ряду кнопок.
	 */
	private Boolean pay;

}
