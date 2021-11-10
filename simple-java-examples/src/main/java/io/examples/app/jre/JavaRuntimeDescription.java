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
