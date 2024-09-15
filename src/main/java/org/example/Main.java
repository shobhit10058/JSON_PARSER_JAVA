package org.example;

import org.example.jsonelement.JsonElement;

public class Main {
    
    private static void runTest(String jsonString) {
        System.out.println("\n");
        JsonHandler jsonHandler = new JsonHandlerImpl();
        JsonElement element = jsonHandler.parse(jsonString);
        System.out.println("Original := " + jsonString);
        System.out.println("generated := " + element.toString());
    }

    public static void main(String[] args) {
        runTest("{\"c\":[-1,2,{\"d\":1}]}");
        runTest("{\"a\":\"1\",\"e\":{\"b\":\"1\",\"c\":[1,2,{\"d\":1}]}}");
    }
}