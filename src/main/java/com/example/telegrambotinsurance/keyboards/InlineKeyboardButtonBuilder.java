package com.example.telegrambotinsurance.keyboards;

public class InlineKeyboardButtonBuilder implements InlineKeyboardButton.Builder {
	private InlineKeyboardButton inlineKeyboardButton;

	public InlineKeyboardButtonBuilder() {
		inlineKeyboardButton = new InlineKeyboardButton();
	}

	public InlineKeyboardButton build(){
		return inlineKeyboardButton;
	}

	public InlineKeyboardButtonBuilder setText(String text) {
		inlineKeyboardButton.setText(text);
		return this;
	}

	public InlineKeyboardButtonBuilder setUrl(String url) {
		inlineKeyboardButton.setUrl(url);
		return this;
	}

	public InlineKeyboardButtonBuilder setLogin_url(String login_url) {
		inlineKeyboardButton.setLogin_url(login_url);
		return this;
	}

	public InlineKeyboardButtonBuilder setCallback_data(String callback_data) {
		inlineKeyboardButton.setCallback_data(callback_data);
		return this;
	}

	public InlineKeyboardButtonBuilder setSwitch_inline_query(String switch_inline_query) {
		inlineKeyboardButton.setSwitch_inline_query(switch_inline_query);
		return this;
	}

	public InlineKeyboardButtonBuilder setSwitch_inline_query_current_chat(String switch_inline_query_current_chat) {
		inlineKeyboardButton.setSwitch_inline_query_current_chat(switch_inline_query_current_chat);
		return this;
	}

	public InlineKeyboardButtonBuilder setCallback_game(CallbackGame callback_game) {
		inlineKeyboardButton.setCallback_game(callback_game);
		return this;
	}

	public InlineKeyboardButtonBuilder setPay(Boolean pay) {
		inlineKeyboardButton.setPay(pay);
		return this;
	}

}
