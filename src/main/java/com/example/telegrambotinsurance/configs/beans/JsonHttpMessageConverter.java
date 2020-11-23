package com.example.telegrambotinsurance.configs.beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.converter.json.AbstractJsonHttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class JsonHttpMessageConverter extends AbstractJsonHttpMessageConverter {
	private static final byte SPACES_IN_OUTPUT_JSON = 2;

	@Override
	protected Object readInternal(Type resolvedType, Reader reader) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuilder stringBuilder = new StringBuilder();

		String line;
		while (bufferedReader.ready()) {
			line = bufferedReader.readLine();
			stringBuilder.append(line)
					.append(System.lineSeparator());
		}

		String jsonString = stringBuilder.toString();
		return new JSONObject(jsonString);
	}

	@Override
	protected void writeInternal(Object object, Type type, Writer writer) throws Exception {
		String jsonString;
		if (object instanceof JSONObject) {
			jsonString = getJsonString((JSONObject) object);
		} else if (object instanceof JSONArray) {
			jsonString = getJsonString((JSONArray) object);
		} else if (object instanceof Collection) {
			jsonString = getJsonString(new JSONArray((Collection<?>) object));
		} else if (object instanceof String) {
			String objectString = object.toString().trim();
			if (objectString.startsWith("{")) {
				jsonString = getJsonString(new JSONObject(objectString));
			} else if (objectString.startsWith("[")) {
				jsonString = getJsonString(new JSONArray(objectString));
			} else {
				jsonString = getJsonString(new JSONObject(String.format("{\"text\":\"%s\"}", object.toString())));
			}
		} else {
			jsonString = getJsonString(new JSONObject(object));
		}

		BufferedWriter bufferedWriter = new BufferedWriter(writer);
		bufferedWriter.write(jsonString);
		bufferedWriter.flush();
	}

	private String getJsonString(JSONArray jsonArray) {
		return jsonArray.toString(SPACES_IN_OUTPUT_JSON);
	}

	private String getJsonString(JSONObject jsonObject) {
		return jsonObject.toString(SPACES_IN_OUTPUT_JSON);
	}
}
