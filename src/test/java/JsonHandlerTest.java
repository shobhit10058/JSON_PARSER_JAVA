import org.example.JsonHandler;
import org.example.JsonHandlerImpl;
import org.example.jsonelement.JsonElement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class JsonHandlerTest {

	@Test
	public void testJsonHandler() {
		JsonHandler jsonHandler = new JsonHandlerImpl();
		JsonElement element = jsonHandler.parse("{\"c\":[-1,2,{\"d\":1}]}");
		assertEquals("{\"c\":[-1,2,{\"d\":1}]}", element.toString());
		element = jsonHandler.parse("{\"a\":\"1\",\"e\":{\"b\":\"1\",\"c\":[1,2,{\"d\":1}]}}");
		assertEquals("{\"a\":\"1\",\"e\":{\"c\":[1,2,{\"d\":1}],\"b\":\"1\"}}", element.toString());
	}
}
