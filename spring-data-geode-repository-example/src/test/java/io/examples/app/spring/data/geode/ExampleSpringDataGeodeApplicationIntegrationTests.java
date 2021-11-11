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

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.examples.app.spring.data.geode.model.User;
import io.examples.app.spring.data.geode.repo.UserRepository;

/**
 * Integration Tests for {@link ExampleSpringDataGeodeApplication} annotation configuration.
 *
 * @author John Blum
 * @see io.examples.app.spring.data.geode.ExampleSpringDataGeodeApplication
 * @see org.springframework.context.annotation.Configuration
 * @see org.springframework.context.annotation.Import
 * @see org.springframework.test.context.ActiveProfiles
 * @see org.springframework.test.context.ContextConfiguration
 * @see org.springframework.test.context.junit4.SpringRunner
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
@ActiveProfiles("annotation-configuration")
@SuppressWarnings("unused")
public class ExampleSpringDataGeodeApplicationIntegrationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void saveAndFindUserIsSuccessful() {

		User janeDoe = User.as("janeDoe").identifiedBy(2L);

		this.userRepository.save(janeDoe);

		User loadedJaneDoe = this.userRepository.findById(janeDoe.getId()).orElse(null);

		assertThat(loadedJaneDoe).isNotNull();
		assertThat(loadedJaneDoe).isNotSameAs(janeDoe);
		assertThat(loadedJaneDoe).isEqualTo(janeDoe);
	}

	@Configuration
	@Import({
		ExampleSpringDataGeodeApplication.ApplicationConfiguration.class,
		ExampleSpringDataGeodeApplication.AnnotationApplicationConfiguration.class
	})
	static class TestConfiguration {  }

}
