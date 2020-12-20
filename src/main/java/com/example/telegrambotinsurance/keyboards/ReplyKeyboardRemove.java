package com.example.telegrambotinsurance.keyboards;

import lombok.Builder;
import lombok.Data;
import org.json.JSONPropertyName;

@Data
@Builder
public class ReplyKeyboardRemove implements Markup {

	/**
	 * Если true, то у пользователя удалится клавиатура
	 *  (пользователь не сможет вызвать эту клавиатуру;
	 *  если вы хотите скрыть клавиатуру от глаз,
	 *  но сохранить ее доступной, используйте one_time_keyboard в ReplyKeyboardMarkup)
	 */
	public final Boolean removeKeyboard = true;

	/**
	 * ЭТА ПЕРЕМЕННАЯ НЕОБЯЗАТЕЛЬНА
	 * Используйте этот параметр,
	 * если вы хотите удалить клавиатуру только для определенных пользователей.
	 * https://core.telegram.org/bots/api#replykeyboardremove
	 *
	 * Пример:
	 * пользователь голосует в опросе, бот возвращает сообщение с подтверждением в ответ на голосование
	 * и удаляет клавиатуру для этого пользователя,
	 * по-прежнему показывая клавиатуру с параметрами опроса пользователям, которые еще не проголосовали.
	 */
	public Boolean selective;

	@JSONPropertyName("remove_keyboard")
	public Boolean getRemoveKeyboard() {
		return removeKeyboard;
	}
}
