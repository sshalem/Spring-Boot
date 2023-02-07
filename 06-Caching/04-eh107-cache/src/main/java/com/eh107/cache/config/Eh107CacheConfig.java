package com.eh107.cache.config;

import java.time.Duration;

import javax.cache.CacheManager;
import javax.cache.Caching;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;
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
public class Eh107CacheConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(Eh107CacheConfig.class);

	@Bean
	public CacheManager eh107CacheManager() {

		/**
		 * This cache Implementation ,Is from YouTube link I saw.
		 * It is mixing between both packages <groupId>javax.cache</groupId> and <groupId>org.ehcache</groupId>
		 * 
		 * Steps to create cache, In this example I configure:
		 *  1. class a class for Cache Event Listener 
		 * 	2. I create 2 CacheConfiguration , For Book and for Person
		 * 	3. I create 2 Configurations of Book and Person from Eh107Configuration
		 * 	4. define a CacheManager
		 * 	5. create cache using cacheManager 
		 */
				
		LOGGER.info(">>>> Eh107CacheConfig configuration <<<<");

		// (1)
		CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
			    .newEventListenerConfiguration(
			    		new CustomCacheEventListener(), 
			    		EventType.CREATED, 
			    		EventType.UPDATED, 
			    		EventType.REMOVED) 
			    .unordered()
			    .asynchronous();		
		
		// (2)
		CacheConfiguration<Object, Book> bookCacheConfiguration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(
						Object.class, 
						Book.class, 
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB).build())
				.withService(cacheEventListenerConfiguration)
				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(60))) // after 60 sec w/o use the row from cache will be deleted
				.build();

		
		CacheConfiguration<Object, Person> personCacheConfiguration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(
						Object.class, 
						Person.class, 
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB).build())
				.withService(cacheEventListenerConfiguration)
				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(120)))
				.build();
		
		// (3)
		javax.cache.configuration.Configuration<Object, Book> bookConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(bookCacheConfiguration);
		javax.cache.configuration.Configuration<Object, Person> personConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(personCacheConfiguration);
		
		// (4) Implementation is from packages of groupId <groupId>javax.cache</groupId>
		CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
		
		// (5)
		cacheManager.createCache("booksStore", bookConfiguration);
		cacheManager.createCache("personStore", personConfiguration);

		return cacheManager;
	}
}
