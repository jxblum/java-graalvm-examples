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
