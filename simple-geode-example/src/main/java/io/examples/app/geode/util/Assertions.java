/*
 * Copyright 2021-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
