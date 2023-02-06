package com.eh107.cache.config;

import javax.cache.CacheManager;
import javax.cache.Caching;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
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

@Configuration
@EnableCaching
public class Eh107CacheConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(Eh107CacheConfig.class);

	@Bean
	public CacheManager eh107CacheManager() {


		/**
		 * This cache Implementation 
		 * Is from YouTube link I saw.
		 * It is mixing between both packages
		 *  <groupId>javax.cache</groupId>
		 *  <groupId>org.ehcache</groupId>
		 */
				
		LOGGER.info(">>>> Eh107CacheConfig configuration <<<<");

		// Cache Event Listener
		CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
			    .newEventListenerConfiguration(new CacheEventLogger(), EventType.CREATED, EventType.UPDATED) 
			    .unordered()
			    .asynchronous();
		
		// This CacheConfiguration is from package <groupId>org.ehcache</groupId>
		CacheConfiguration<Long, Book> cacheConfiguration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(
						Long.class, 
						Book.class, 
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB).build())
				.withService(cacheEventListenerConfiguration)
//				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
				.build();

		// CachingProvider & CacheManager Implementation is from packages of groupId <groupId>javax.cache</groupId>
		CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
		
		javax.cache.configuration.Configuration<Long, Book> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration);

		cacheManager.createCache("booksStore", configuration);
		cacheManager.createCache("personStore", configuration);

		return cacheManager;
	}
}
