package io.examples.app.js.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

/**
 * The {@link PrettyPrintJSON} class is a Java program combining JavaScript libraries to pretty print JSON data.
 *
 * @author John Blum
 * @see java.io.InputStream
 * @see org.graalvm.polyglot.Context
 * @since 1.0.0
 */
public class PrettyPrintJSON {

	private static final String TEST_JSON_FILENAME = "/test.json";

	public static void main(String[] args) {

		InputStream testJsonInputStream = PrettyPrintJSON.class.getResourceAsStream(TEST_JSON_FILENAME);

		testJsonInputStream = testJsonInputStream != null ? testJsonInputStream : System.in;

		BufferedReader reader = new BufferedReader(new InputStreamReader(testJsonInputStream));

		String input = reader.lines()
			.collect(Collectors.joining(System.lineSeparator()));

		try (Context context = Context.create("js")) {

			Value parse = context.eval("js", "JSON.parse");
			Value stringify = context.eval("js", "JSON.stringify");
			Value result = stringify.execute(parse.execute(input), null, 2);

			System.out.println(result);
		}
	}
}
