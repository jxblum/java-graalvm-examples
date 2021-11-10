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

package io.examples.app.jre;

/**
 * The {@link JavaRuntimeDescription} class is a simple Java program that prints the Java Runtime Environment (JRE)
 * metadata.
 *
 * @author John Blum
 * @see java.lang.System#getProperties()
 * @since 1.0.0
 */
public class JavaRuntimeDescription {

	public static void main(String[] args) {

		System.out.printf("Java Vendor [%s]%n", System.getProperty("java.vendor"));
		System.out.printf("Java Home [%s]%n", System.getProperty("java.home"));
		System.out.printf("Java Version [%s]%n", System.getProperty("java.version"));
		System.out.printf("Java Compiler [%s]%n", System.getProperty("java.compiler"));
		System.out.printf("JVM Name [%s]%n", System.getProperty("java.vm.name"));
		System.out.printf("JVM Vendor [%s]%n", System.getProperty("java.vm.vendor"));
		System.out.printf("JVM Version [%s]%n", System.getProperty("java.vm.version"));
	}
}
