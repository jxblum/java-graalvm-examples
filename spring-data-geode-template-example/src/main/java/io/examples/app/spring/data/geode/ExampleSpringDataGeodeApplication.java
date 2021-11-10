package io.examples.app.spring.data.geode;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serializable;

import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.data.gemfire.GemfireUtils;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Example Spring Data for Apache Geode (SDG) Java application (program) that uses the {@link GemfireTemplate}
 * to access (store/persist and read) data from Apache Geode.
 *
 * @author John Blum
 * @see org.apache.geode.cache.GemFireCache
 * @see org.apache.geode.cache.Region
 * @see org.springframework.context.ConfigurableApplicationContext
 * @see org.springframework.context.annotation.AnnotationConfigApplicationContext
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.data.gemfire.GemfireTemplate
 * @see org.springframework.data.gemfire.client.ClientRegionFactoryBean
 * @see org.springframework.data.gemfire.config.annotation.ClientCacheApplication
 * @see org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ExampleSpringDataGeodeApplication implements Runnable {

	protected static final String USERS_REGION_NAME = "Users";

	public static void main(String[] args) {
		new ExampleSpringDataGeodeApplication(args).run();
	}

	@ClientCacheApplication(copyOnRead = true)
	//@EnableEntityDefinedRegions(basePackageClasses = User.class, clientRegionShortcut = ClientRegionShortcut.LOCAL)
	static class ApplicationConfiguration {

		@Bean(USERS_REGION_NAME)
		ClientRegionFactoryBean<Long, User> usersRegion(GemFireCache cache) {

			ClientRegionFactoryBean<Long, User> usersRegion = new ClientRegionFactoryBean<>();

			usersRegion.setCache(cache);
			usersRegion.setShortcut(ClientRegionShortcut.LOCAL);

			return usersRegion;
		}

		@Bean
		@DependsOn(USERS_REGION_NAME)
		GemfireTemplate usersTemplate(GemFireCache cache) {
			return new GemfireTemplate(cache.getRegion(GemfireUtils.toRegionPath(USERS_REGION_NAME)));
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

	public ExampleSpringDataGeodeApplication(@NonNull String... arguments) {
		this(newApplicationContext(ApplicationConfiguration.class), arguments);
	}

	public ExampleSpringDataGeodeApplication(@NonNull ConfigurableApplicationContext applicationContext,
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
		System.out.printf(message, arguments);
		System.out.flush();
	}

	@Override
	public void run() {
		run(getApplicationContext().getBean("usersTemplate", GemfireTemplate.class), getArguments());
	}

	protected void run(@NonNull GemfireTemplate usersTemplate, @NonNull String[] arguments) {

		User jonDoe = User.as("jonDoe").identifiedBy(1L);

		log("Saving User [%s]...%n", jonDoe);

		usersTemplate.put(jonDoe.getId(), jonDoe);

		log("Finding User [%s] by ID [%d]...%n", jonDoe, jonDoe.getId());

		User loadedJonDoe = usersTemplate.get(jonDoe.getId());

		assertThat(loadedJonDoe).isNotNull();
		assertThat(loadedJonDoe).isNotSameAs(jonDoe);
		assertThat(loadedJonDoe).isEqualTo(jonDoe);

		log("SUCCESS!!%n");
	}

	@Getter
	@EqualsAndHashCode
	@Region(USERS_REGION_NAME)
	@RequiredArgsConstructor(staticName = "as")
	static class User implements Serializable {

		@Id
		@Setter(AccessLevel.PRIVATE)
		private Long id;

		@lombok.NonNull
		private final String name;

		@NonNull User identifiedBy(@Nullable Long id) {
			setId(id);
			return this;
		}

		@Override
		public String toString() {
			return getName();
		}
	}
}
