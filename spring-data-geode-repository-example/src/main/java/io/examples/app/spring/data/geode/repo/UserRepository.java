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
package io.examples.app.spring.data.geode.repo;

import org.springframework.data.repository.CrudRepository;

import io.examples.app.spring.data.geode.model.User;

/**
 * Data Access Object (DAO) and Spring Data {@link CrudRepository} used to perform basic CRUD
 * and simple query data access operations on {@link User Users}.
 *
 * @author John Blum
 * @see io.examples.app.spring.data.geode.model.User
 * @see org.springframework.data.repository.CrudRepository
 * @since 1.0.0
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
