package org.example.jsonelement;

import static org.example.jsonelement.JsonElementType.MAP;

import java.util.Map;

public class MapJsonElement extends JsonElement {
	@Override
	public JsonElementType getType() {
		return MAP;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String toString() {
		String stringValue = "{";
		Map<String, JsonElement> jsonMap = (Map<String, JsonElement>) getValue();
		int sepToAdd = jsonMap.size() - 1;
		for (String key: jsonMap.keySet()) {
			JsonElement keyValue = jsonMap.get(key);
			stringValue += key + ":";
			stringValue += keyValue.toString();
			if (sepToAdd > 0) {
				stringValue += ",";
				sepToAdd -= 1;
			}
		}
		stringValue += "}";
		return stringValue;
	}

	@SuppressWarnings("unchecked")
	public void putElement(String key, JsonElement element) {
		((Map<String, JsonElement>) getValue()).put(key, element);
	}
}
