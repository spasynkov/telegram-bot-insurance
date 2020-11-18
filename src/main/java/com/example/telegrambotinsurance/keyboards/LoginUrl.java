package com.example.telegrambotinsurance.keyboards;

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
	private String forward_text;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Логин бота, который будет использоваться для авторизации пользователя.
	 * См. Подробности в разделе «Настройка бота». -> https://core.telegram.org/widgets/login#setting-up-a-bot
	 * Если не указано, будет использоваться имя текущего бота.
	 * Домен URL-адреса должен совпадать с доменом, связанным с ботом.
	 * Дополнительные сведения см. В разделе Telegram API «Связывание домена с ботом».
	 * -> https://core.telegram.org/widgets/login#linking-your-domain-to-the-bot
	 */
	private String bot_username;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Передайте True, чтобы запросить у вашего бота разрешение на отправку сообщений пользователю.
	 */
	private Boolean request_write_access;

	public LoginUrl(String url) {
		this.url = url;
	}

	public LoginUrl(String url, String forward_text, String bot_username, Boolean request_write_access) {
		this.url = url;
		this.forward_text = forward_text;
		this.bot_username = bot_username;
		this.request_write_access = request_write_access;
	}

	public LoginUrl(String url, String forward_text, String bot_username) {
		this.url = url;
		this.forward_text = forward_text;
		this.bot_username = bot_username;
	}

	public LoginUrl(String url, String forward_text) {
		this.url = url;
		this.forward_text = forward_text;
	}
}
