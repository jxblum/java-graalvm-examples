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

package io.examples.app.spring.data.geode;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serializable;

import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.mapping.GemfireMappingContext;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.gemfire.repository.support.GemfireRepositoryFactoryBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Example Spring Data for Apache Geode (SDG) Java application (program) that uses Spring Data
 * {@link CrudRepository Repositories} to access (store/persist and read) data from Apache Geode.
 *
 * @author John Blum
 * @see org.apache.geode.cache.GemFireCache
 * @see org.apache.geode.cache.Region
 * @see org.springframework.context.ConfigurableApplicationContext
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.Import
 * @see org.springframework.context.annotation.Profile
 * @see org.springframework.context.annotation.AnnotationConfigApplicationContext
 * @see org.springframework.data.gemfire.client.ClientRegionFactoryBean
 * @see org.springframework.data.gemfire.config.annotation.ClientCacheApplication
 * @see org.springframework.data.gemfire.mapping.GemfireMappingContext
 * @see org.springframework.data.gemfire.repository.config.EnableGemfireRepositories
 * @see org.springframework.data.repository.CrudRepository
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ExampleSpringDataGeodeApplication implements Runnable {

	private static final String ACTIVE_SPRING_PROFILE = "java-configuration";

	public static void main(String[] args) {
		new ExampleSpringDataGeodeApplication(args).run();
	}

	@ClientCacheApplication(name = "ExampleSpringDataGeodeApplicationUsingRepositories", copyOnRead = true)
	@Import(JavaApplicationConfiguration.class)
	//@Import({ AnnotationApplicationConfiguration.class, JavaApplicationConfiguration.class })
	static class ApplicationConfiguration { }

	/*
	@Configuration
	@Profile("annotation-configuration")
	@EnableEntityDefinedRegions(basePackageClasses = User.class, clientRegionShortcut = ClientRegionShortcut.LOCAL)
	@EnableGemfireRepositories(basePackageClasses = UserRepository.class)
	static class AnnotationApplicationConfiguration { }
	*/

	@Configuration
	@Profile("java-configuration")
	static class JavaApplicationConfiguration {

		@Bean("Users")
		ClientRegionFactoryBean<Object, Object> usersRegion(GemFireCache gemfireCache) {

			ClientRegionFactoryBean<Object, Object> usersRegion = new ClientRegionFactoryBean<>();

			usersRegion.setCache(gemfireCache);
			usersRegion.setShortcut(ClientRegionShortcut.LOCAL);

			return usersRegion;
		}

		@Bean
		GemfireRepositoryFactoryBean<CrudRepository<User, Long>, User, Long> userRepository() {

			GemfireRepositoryFactoryBean<CrudRepository<User, Long>, User, Long> userRepository =
				new GemfireRepositoryFactoryBean<>(UserRepository.class);

			userRepository.setGemfireMappingContext(new GemfireMappingContext());

			return userRepository;
		}
	}

	private static @NonNull ConfigurableApplicationContext newApplicationContext(Class<?>... configurationClasses) {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

		applicationContext.getEnvironment().addActiveProfile(ACTIVE_SPRING_PROFILE);
		applicationContext.register(configurationClasses);
		applicationContext.registerShutdownHook();
		applicationContext.refresh();

		return applicationContext;
	}

	@Getter(AccessLevel.PROTECTED)
	private final ConfigurableApplicationContext applicationContext;

	@Getter(AccessLevel.PROTECTED)
	private final String[] arguments;

	public ExampleSpringDataGeodeApplication(@NonNull String... arguments) {
		this(newApplicationContext(ApplicationConfiguration.class), arguments);
	}

	public ExampleSpringDataGeodeApplication(@NonNull ConfigurableApplicationContext applicationContext,
			@NonNull String... arguments) {

		Assert.notNull(applicationContext, "Spring ApplicationContext is required");
		Assert.notNull(arguments, "Program arguments are required");

		this.applicationContext = applicationContext;
		this.arguments = arguments;
	}

	protected void log(String message, Object... arguments) {
		System.out.printf(message, arguments);
		System.out.flush();
	}

	@Override
	public void run() {
		run(getApplicationContext().getBean(UserRepository.class), getArguments());
	}

	protected void run(@NonNull UserRepository userRepository, @NonNull String[] arguments) {

		User jonDoe = User.as("jonDoe").identifiedBy(1L);

		log("Saving User [%s]...%n", jonDoe);

		userRepository.save(jonDoe);

		log("Finding User [%s] by ID [%d]...%n", jonDoe, jonDoe.getId());

		User loadedJonDoe = userRepository.findById(jonDoe.getId()).orElse(null);

		assertThat(loadedJonDoe).isNotNull();
		assertThat(loadedJonDoe).isNotSameAs(jonDoe);
		assertThat(loadedJonDoe).isEqualTo(jonDoe);

		log("SUCCESS!!%n");
	}
}

@Getter
@Region("Users")
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "as")
class User implements Serializable {

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

interface UserRepository extends CrudRepository<User, Long> { }
