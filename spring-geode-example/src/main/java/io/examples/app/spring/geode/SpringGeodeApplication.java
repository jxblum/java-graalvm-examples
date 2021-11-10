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

package io.examples.app.spring.geode;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.client.ClientRegionShortcut;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * A {@link SpringBootApplication} (SBDG) using Apache Geode to save (persist) and load (read) data.
 *
 * @author John Blum
 * @see org.apache.geode.cache.GemFireCache
 * @see org.springframework.boot.ApplicationRunner
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.boot.builder.SpringApplicationBuilder
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.Profile
 * @see org.springframework.data.gemfire.client.ClientRegionFactoryBean
 * @see org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions
 * @see org.springframework.data.repository.CrudRepository
 * @since 1.0.0
 */
@SpringBootApplication
@SuppressWarnings("unused")
public class SpringGeodeApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(SpringGeodeApplication.class)
			.profiles("java-configuration")
			.web(WebApplicationType.NONE)
			.build()
			.run(args);
	}

	@Configuration
	@Profile("annotation-configuration")
	@EnableEntityDefinedRegions(basePackageClasses = User.class, clientRegionShortcut = ClientRegionShortcut.LOCAL)
	static class AnnotationApplicationConfiguration { }

	@Configuration
	@Profile("java-configuration")
	static class JavaApplicationConfiguration {

		@Bean("Users")
		public ClientRegionFactoryBean<Object, Object> usersRegion(GemFireCache gemfireCache) {

			ClientRegionFactoryBean<Object, Object> clientRegion = new ClientRegionFactoryBean<>();

			clientRegion.setCache(gemfireCache);
			clientRegion.setShortcut(ClientRegionShortcut.LOCAL);

			return clientRegion;
		}
	}

	protected void log(String message, Object... arguments) {
		System.out.printf(message, arguments);
		System.out.flush();
	}

	@Bean
	@NonNull ApplicationRunner runner(@NonNull UserRepository userRepository) {

		return args -> {

			User jonDoe = User.as("jonDoe").identifiedBy(1L);

			log("Saving User [%s]...%n", jonDoe);

			userRepository.save(jonDoe);

			User loadedJonDoe = userRepository.findById(jonDoe.getId()).orElse(null);

			log("Loaded User [%1$s] with ID [%2$d]%n", loadedJonDoe, jonDoe.getId());

			assertThat(loadedJonDoe).isNotNull();
			assertThat(loadedJonDoe).isEqualTo(jonDoe);

			log("SUCCESS!!%n");
		};
	}
}

@Getter
@Region("Users")
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "as")
class User {

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
