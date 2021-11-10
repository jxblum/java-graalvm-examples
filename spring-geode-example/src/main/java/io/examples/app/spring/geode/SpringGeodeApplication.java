package io.examples.app.spring.geode;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.geode.cache.client.ClientRegionShortcut;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A {@link SpringGeodeApplication } using Apache Geode to save and load data.
 *
 * @author John Blum
 * @see org.springframework.boot.ApplicationRunner
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.context.annotation.Bean
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.data.gemfire.mapping.annotation.Region
 * @since 1.0.0
 */
@SpringBootApplication
@SuppressWarnings("unused")
public class SpringGeodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGeodeApplication.class);
	}

	@Configuration
	@EnableEntityDefinedRegions(basePackageClasses = User.class, clientRegionShortcut = ClientRegionShortcut.LOCAL)
	static class ApplicationConfiguration { }

	@Bean
	@NonNull ApplicationRunner runner(@NonNull UserRepository userRepository) {

		return args -> {

			User jonDoe = User.of("jonDoe").identifiedBy(1L);

			System.out.printf("Saving [%s]...%n", jonDoe);

			userRepository.save(jonDoe);

			User loadedJonDoe = userRepository.findById(jonDoe.getId()).orElse(null);

			System.out.printf("Loaded [%s]%n", loadedJonDoe);

			assertThat(loadedJonDoe).isNotNull();
			assertThat(loadedJonDoe).isEqualTo(jonDoe);

			System.out.println("SUCCESS!!");
		};
	}
}

@Getter
@ToString(of = "name")
@Region("Users")
@RequiredArgsConstructor(staticName = "of")
class User {

	@Setter(AccessLevel.PRIVATE)
	private Long id;

	@lombok.NonNull
	private final String name;

	@NonNull User identifiedBy(@Nullable Long id) {
		setId(id);
		return this;
	}
}

interface UserRepository extends CrudRepository<User, Long> { }
