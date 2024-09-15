package org.example;

import org.example.jsonelement.JsonElement;

public interface JsonHandler {
	String stringify(Object json);
	JsonElement parse(String json);
}