package io.examples.app.spring.boot;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Example {@link SpringBootApplication}.
 *
 * @author John Blum
 * @see org.springframework.boot.ApplicationRunner
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.context.annotation.Bean
 * @since 1.0.0
 */
@SpringBootApplication
@SuppressWarnings("unused")
public class ExampleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringBootApplication.class);
	}

	@Bean
	ApplicationRunner runner() {
		return args -> System.out.println("Hello Spring Boot!");
	}
}
