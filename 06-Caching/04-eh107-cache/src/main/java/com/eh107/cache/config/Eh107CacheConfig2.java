package com.eh107.cache.config;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

import org.ehcache.config.CacheRuntimeConfiguration;
import org.ehcache.jsr107.Eh107Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eh107.cache.entity.Book;
import com.eh107.cache.entity.Person;

@Configuration
@EnableCaching
public class Eh107CacheConfig2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Eh107CacheConfig2.class);

	@Bean
	@SuppressWarnings("unchecked")
	public CacheManager eh107CacheManager2() {

		LOGGER.info(">>>> Eh107CacheConfig2 configuration <<<<");

		CachingProvider provider = Caching.getCachingProvider();
		CacheManager cacheManager = provider.getCacheManager();

		MutableConfiguration<Long, Book> bookConfiguration = new MutableConfiguration<>();
		MutableConfiguration<Long, Person> personConfiguration = new MutableConfiguration<>();

		Cache<Long, Book> bookCache = cacheManager.createCache("booksStore", setConfigurationBook(bookConfiguration));
		Cache<Long, Person> personCache = cacheManager.createCache("personsStore", setConfigurationPerson(personConfiguration));

		Eh107Configuration<Long, String> eh107ConfigurationBook = bookCache.getConfiguration(Eh107Configuration.class);
		Eh107Configuration<Long, String> eh107ConfigurationPerson = personCache.getConfiguration(Eh107Configuration.class);

		eh107ConfigurationBook.unwrap(CacheRuntimeConfiguration.class);
		eh107ConfigurationPerson.unwrap(CacheRuntimeConfiguration.class);

		return cacheManager;
	}

	private MutableConfiguration<Long, Book> setConfigurationBook(
			MutableConfiguration<Long, Book> mutableConfiguration) {
		mutableConfiguration.setTypes(Long.class, Book.class);
		mutableConfiguration.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
		return mutableConfiguration;
	}

	private MutableConfiguration<Long, Person> setConfigurationPerson(
			MutableConfiguration<Long, Person> mutableConfiguration) {
		mutableConfiguration.setTypes(Long.class, Person.class);
		mutableConfiguration.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
		return mutableConfiguration;
	}

}
