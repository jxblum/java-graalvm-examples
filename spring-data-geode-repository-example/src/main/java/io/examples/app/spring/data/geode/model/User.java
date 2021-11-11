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
package io.examples.app.spring.data.geode.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Abstract Data Type (ADT) modeling a {@literal user} of an application or system.
 *
 * @author John Blum
 * @see org.springframework.data.annotation.Id
 * @see org.springframework.data.gemfire.mapping.annotation.Region
 * @since 1.0.0
 */
@Getter
@Region("Users")
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "as")
public class User implements Serializable {

	@Id
	@Setter(AccessLevel.PRIVATE)
	private Long id;

	@lombok.NonNull
	private final String name;

	public @NonNull User identifiedBy(@Nullable Long id) {
		setId(id);
		return this;
	}

	@Override
	public String toString() {
		return getName();
	}
}
