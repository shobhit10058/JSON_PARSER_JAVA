Implementing Json Library in Java

```
public interface JsonHandler {
	String stringify(Object json);
	JsonElement parse(String json);
}
```
