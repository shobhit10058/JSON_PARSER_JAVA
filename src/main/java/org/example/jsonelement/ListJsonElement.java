package org.example.jsonelement;
import static org.example.jsonelement.JsonElementType.LIST;

import java.util.List;

@SuppressWarnings("unchecked")
public class ListJsonElement extends JsonElement {

	@Override
	public JsonElementType getType() {
		return LIST;
	}

	@Override
	public String toString() {
		String stringValue = "[";
		List<JsonElement> jsonList = (List<JsonElement>) getValue();
		for (int i = 0; i < jsonList.size(); i += 1) {
			JsonElement listValue = jsonList.get(i);
			stringValue += listValue.toString();
			if (i < jsonList.size() - 1) {
				stringValue += ",";
			}
		}
		stringValue += "]";
		return stringValue;
	}

	public void addElement(JsonElement element) {
		((List<JsonElement>) getValue()).add(element);
	}
}