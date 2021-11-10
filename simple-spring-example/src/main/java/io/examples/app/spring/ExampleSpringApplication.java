package io.examples.app.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import io.examples.app.spring.service.HelloService;

/**
 * A simple Spring Java Application (program).
 *
 * @author John Blum
 * @see java.lang.Runnable
 * @see org.springframework.context.ConfigurableApplicationContext
 * @see org.springframework.context.annotation.AnnotationConfigApplicationContext
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.context.annotation.Configuration
 * @see io.examples.app.spring.service.HelloService
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ExampleSpringApplication implements Runnable {

	public static void main(String[] args) {
		new ExampleSpringApplication(args).run();
	}

	@Configuration
	static class ApplicationConfiguration {

		@Bean
		public HelloService helloService() {
			return new HelloService() { };
		}
	}

	private static @NonNull ConfigurableApplicationContext newApplicationContext(Class<?>... configurationClasses) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.register(configurationClasses);
		applicationContext.registerShutdownHook();
		applicationContext.refresh();

		return applicationContext;
	}

	private final ConfigurableApplicationContext applicationContext;

	private final String[] arguments;

	public ExampleSpringApplication(@NonNull String... arguments) {
		this(newApplicationContext(ApplicationConfiguration.class), arguments);
	}

	public ExampleSpringApplication(@NonNull ConfigurableApplicationContext applicationContext,
			@NonNull String... arguments) {

		Assert.notNull(applicationContext, "Spring ApplicationContext is required");

		this.applicationContext = applicationContext;
		this.arguments = arguments;
	}

	protected @NonNull ConfigurableApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	protected @NonNull String[] getArguments() {
		return this.arguments != null ? this.arguments : new String[0];
	}

	protected void log(String message, Object... arguments) {
		System.out.printf(message + "%n", arguments);
		System.out.flush();
	}

	@Override
	public void run() {

		String[] arguments = getArguments();

		String name = arguments.length > 0 ? arguments[0] : null;

		HelloService helloService = getApplicationContext().getBean(HelloService.class);

		run(helloService, name);
	}

	protected void run(@NonNull HelloService helloService, @Nullable String name) {
		log(helloService.sayHelloTo(name));
	}
}
