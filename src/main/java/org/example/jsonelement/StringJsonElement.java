package org.example.jsonelement;

import static org.example.jsonelement.JsonElementType.STRING;

public class StringJsonElement extends JsonElement{

	@Override
	public JsonElementType getType() {
		return STRING;
	}

	@Override
	public String toString() {
		return (String) value;
	}
}
