package io.examples.app.geode.util;

import java.util.Objects;

/**
 * Abstract utility class containing assertions.
 *
 * @author John Blum
 * @see java.util.Objects
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public abstract class Assertions {

	public static void assertEquals(Object target, Object source) {

		if (!Objects.equals(target, source)) {
			throw new AssertionError(String.format("Expected target [%1$s] to be equal to source [%2$s]",
				target, source));
		}
	}

	public static void assertNotNull(Object target, String description, Object... args) {

		if (Objects.isNull(target)) {
			throw new AssertionError(String.format(description, args));
		}
	}

	public static void assertNotSame(Object target, Object source) {

		if (target == source) {
			throw new AssertionError(String.format("Expected target [%1$s] to not be the same as source [%2$s]",
				System.identityHashCode(target), System.identityHashCode(source)));
		}
	}

}
