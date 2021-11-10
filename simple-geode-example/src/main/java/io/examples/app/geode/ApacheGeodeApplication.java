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

package io.examples.app.geode;

import java.io.Serializable;
import java.util.Properties;

import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.distributed.ConfigurationProperties;

import io.examples.app.geode.util.Assertions;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * The {@link ApacheGeodeApplication} class is a Java program that bootstraps (configures & initializes) Apache Geode,
 * stores then retrieves data in a local Apache Geode {@link ClientCache}, and prints the result.
 *
 * @author John Blum
 * @see java.lang.Runnable
 * @see java.io.Serializable
 * @see java.util.Properties
 * @see org.apache.geode.cache.GemFireCache
 * @see org.apache.geode.cache.Region
 * @see org.apache.geode.cache.client.ClientCache
 * @see org.apache.geode.cache.client.ClientCacheFactory
 * @see org.apache.geode.distributed.ConfigurationProperties
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ApacheGeodeApplication implements Runnable {

	protected static final String LOG_LEVEL = "INFO";
	protected static final String USERS_REGION_NAME = "Users";

	public static void main(String[] args) {
		new ApacheGeodeApplication().run();

	}

	private static Properties newGemFireProperties(String name) {

		Properties gemfireProperties = new Properties();

		gemfireProperties.setProperty(ConfigurationProperties.NAME, name);
		gemfireProperties.setProperty(ConfigurationProperties.LOG_LEVEL, LOG_LEVEL);

		return gemfireProperties;
	}

	private static ClientCache newClientCache(Properties gemfireProperties) {

		ClientCache clientCache = new ClientCacheFactory(gemfireProperties).create();

		clientCache.setCopyOnRead(true);

		return clientCache;
	}

	private static ClientCache newUsersRegion(ClientCache clientCache) {

		clientCache.<Long, User>createClientRegionFactory(ClientRegionShortcut.LOCAL)
			.create(USERS_REGION_NAME);

		return clientCache;
	}

	@Getter(AccessLevel.PROTECTED)
	private final GemFireCache cache;

	public ApacheGeodeApplication() {
		this(newUsersRegion(newClientCache(
			newGemFireProperties(ApacheGeodeApplication.class.getSimpleName()))));
	}

	public ApacheGeodeApplication(GemFireCache cache) {
		Assertions.assertNotNull(cache, "GemFireCache is required");
		this.cache = cache;
	}

	@Override
	public void run() {
		run(getCache());
	}

	private void log(String message, Object... args) {
		System.out.printf(message, args);
		System.out.flush();
	}

	protected void run(GemFireCache cache) {

		Region<Long, User> users = cache.getRegion(USERS_REGION_NAME);

		User jonDoe = User.of("jonDoe").identifiedBy(1L);

		log("Saving User [%s]...%n", jonDoe);

		users.put(jonDoe.getId(), jonDoe);

		log("Loading User with ID [%d]...%n", jonDoe.getId());

		User loadedJonDoe = users.get(jonDoe.getId());

		Assertions.assertNotNull(loadedJonDoe, "Loaded jonDoe was null");
		Assertions.assertNotSame(loadedJonDoe, jonDoe);
		Assertions.assertEquals(loadedJonDoe, jonDoe);

		System.out.println("SUCCESS!!");
	}
}

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
class User implements Serializable {

	@Setter(AccessLevel.PRIVATE)
	private Long id;

	@lombok.NonNull
	private final String name;

	public User identifiedBy(Long id) {
		setId(id);
		return this;
	}

	@Override
	public String toString() {
		return getName();
	}
}
