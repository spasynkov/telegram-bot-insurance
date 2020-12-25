package com.example.telegrambotinsurance.keyboards;

import org.json.JSONPropertyName;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class LoginUrl {
	/**
	 * URL-адрес HTTP, который открывается с данными авторизации пользователя,
	 * добавленными в строку запроса при нажатии кнопки.
	 * Если пользователь отказывается предоставить данные авторизации,
	 * будет открыт исходный URL без информации о пользователе.
	 * Добавляемые данные такие же, как описано в разделе Telegram API «Получение данных авторизации». ->
	 *  https://core.telegram.org/widgets/login#receiving-authorization-data
	 *
	 *
	 * ПРИМЕЧАНИЕ.
	 * Вы всегда должны проверять хэш полученных данных для проверки аутентификации
	 * и целостности данных, как описано в разделе Telegram API «Проверка авторизации». ->
	 *  https://core.telegram.org/widgets/login#checking-authorization .
	 */
	private String url;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Текст кнопки в перенаправленных сообщениях.
	 */
	@Getter(onMethod_ = @JSONPropertyName("forward_text"))
	private String forwardText;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Логин бота, который будет использоваться для авторизации пользователя.
	 * См. Подробности в разделе «Настройка бота». -> https://core.telegram.org/widgets/login#setting-up-a-bot
	 * Если не указано, будет использоваться имя текущего бота.
	 * Домен URL-адреса должен совпадать с доменом, связанным с ботом.
	 * Дополнительные сведения см. В разделе Telegram API «Связывание домена с ботом».
	 * -> https://core.telegram.org/widgets/login#linking-your-domain-to-the-bot
	 */
	@Getter(onMethod_ = @JSONPropertyName("bot_username"))
	private String botUsername;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Передайте True, чтобы запросить у вашего бота разрешение на отправку сообщений пользователю.
	 */
	@Getter(onMethod_ = @JSONPropertyName("request_write_access"))
	private Boolean requestWriteAccess;
}
