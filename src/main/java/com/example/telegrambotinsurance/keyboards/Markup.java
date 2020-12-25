package com.example.telegrambotinsurance.keyboards;

import org.json.JSONObject;

public interface Markup {
	default JSONObject toJson() {
		return new JSONObject(this);
	}
}
