package io.examples.app.spring.service;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * The {@link HelloService} interface is a Spring {@link Service} that says {@literal hello} to
 * a {@link String named} person, place or thing.
 *
 * @author John Blum
 * @see org.springframework.stereotype.Service
 * @since 1.0.0
 */
@Service
@SuppressWarnings("unused")
public interface HelloService {

	String SPRING_CONSTANT = "Spring";

	default String sayHelloTo(@Nullable String name) {
		return String.format("Hello %s!%n", defaultSayHelloToSpring(name));
	}

	default @NonNull String defaultSayHelloToSpring(String name) {
		return StringUtils.hasText(name) ? name : SPRING_CONSTANT;
	}

}
