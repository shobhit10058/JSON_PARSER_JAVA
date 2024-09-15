package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.example.jsonelement.JsonElement;
import static org.example.jsonelement.JsonElementType.MAP;
import static org.example.jsonelement.JsonElementType.STRING;
import org.example.jsonelement.ListJsonElement;
import org.example.jsonelement.MapJsonElement;
import org.example.jsonelement.StringJsonElement;
import org.example.utilityclasses.Tuple3;

public class JsonHandlerImpl implements JsonHandler {
	
	@SuppressWarnings("unchecked")
	@Override
	public String stringify(Object json) {
		String jsonString = "";
		if (json instanceof Number jsonValue) {
			return jsonValue.toString();
		}
		if (json instanceof String jsonValue) {
			return jsonValue;
		}
		if (json instanceof Map map) {
			jsonString += "{";
			Map<String, Object> jsonMap = map;
			for (String key: jsonMap.keySet()) {
				Object keyValue = jsonMap.get(key);
				jsonString += stringify(keyValue);
			}
			jsonString += "}";
			return jsonString;
		}
		if (json instanceof Collection collection) {
			jsonString += "[";
			for (Object value: collection) {
				jsonString += stringify(value);
			}
			jsonString += "]";
			return jsonString;
		}
		throw new RuntimeException("INVALID_ELEMENT_TYPE");
	}

	@Override
	public JsonElement parse(String jsonString) {
		Stack<Tuple3<Character, String, JsonElement> > stack = new Stack<>();
		JsonElement jsonElement = null;
		String currentValue = "";
		String currentKey = "";

		for (int i = 0; i < jsonString.length(); i += 1) {
			switch (jsonString.charAt(i)) {
					case '[', '{' -> {
								Tuple3<Character, String, JsonElement> tuple3 = new Tuple3<>(jsonString.charAt(i), currentKey, null);
								if (jsonString.charAt(i) == '[') {
									ListJsonElement listJsonElement = new ListJsonElement();
									listJsonElement.setValue(new ArrayList<>());
									tuple3.setThird(listJsonElement);
								} else if (jsonString.charAt(i) == '{') {
									MapJsonElement mapJsonElement = new MapJsonElement();
									mapJsonElement.setValue(new HashMap<>());
									tuple3.setThird(mapJsonElement);
								}
								stack.add(tuple3);
								currentValue = "";
							}
					case ']', '}' -> {
									JsonElement lastElement = stack.peek().getThird();
									currentKey = stack.peek().getSecond();
									if (lastElement.getType() == STRING) {
										stack.pop();
										addLastElementToStack(stack.peek().getThird(), currentKey, lastElement);
									}

									Tuple3<Character, String, JsonElement> tuple3 = stack.peek();
									currentKey = tuple3.getSecond();
									if (!validateOpposites(tuple3.getFirst(), jsonString.charAt(i))) {
										throw new RuntimeException("INVALID_JSON_STRING");
									}
									stack.pop();
									if (stack.isEmpty()) {
										jsonElement = tuple3.getThird();
									} else {
										addLastElementToStack(stack.peek().getThird(), currentKey, tuple3.getThird());
									}
									currentValue = "";
								}
					case ',' -> {
									JsonElement lastElement = stack.peek().getThird();
									currentKey = stack.peek().getSecond();
									if (lastElement.getType() == STRING) {
										stack.pop();
										addLastElementToStack(stack.peek().getThird(), currentKey, lastElement);
									}
									currentValue = "";
								}
					case ':' -> {
									// current value will be key
									currentValue = "";
									JsonElement lastElement = stack.peek().getThird();
									if (lastElement.getType() == STRING) {
										currentKey = (String) lastElement.getValue();
										stack.pop();
									} else {
										throw new RuntimeException("INVALID_JSON_STRING");
									}
								}
					default -> {
							currentValue += jsonString.charAt(i);
							JsonElement lastElement = stack.peek().getThird();
							if (lastElement.getType() == STRING) {
								lastElement.setValue(currentValue);
							} else {
								StringJsonElement stringJsonElement = new StringJsonElement();
								stringJsonElement.setValue(currentValue);
								stack.add(new Tuple3<>(jsonString.charAt(i), currentKey, stringJsonElement));
							}
					}
			}
		}
		return jsonElement;
	}

	private boolean validateOpposites(Character a, Character b) {
		return (a == '{' && b == '}') || (a == '[' && b == ']');
	}

	private void addLastElementToStack(JsonElement elementToAddIn, String currentKey, JsonElement elementToAdd) {
		if (elementToAddIn.getType() == MAP) {
			((MapJsonElement) elementToAddIn).putElement(currentKey, elementToAdd);
		} else {
			((ListJsonElement) elementToAddIn).addElement(elementToAdd);
		}
	}
}
