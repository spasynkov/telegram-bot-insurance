package com.example.telegrambotinsurance.keyboards;

public interface InlineButtonInterface extends Button {

	static InlineKeyboardButton.Builder builder(){
		return new InlineKeyboardButtonBuilder();
	}
}
