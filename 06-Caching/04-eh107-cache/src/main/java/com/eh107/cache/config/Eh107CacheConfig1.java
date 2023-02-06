package com.eh107.cache.config;

import java.time.Duration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eh107.cache.entity.Book;

@Configuration
@EnableCaching
public class Eh107CacheConfig1 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Eh107CacheConfig1.class);

	@Bean
	public CacheManager eh107CacheManager() {

		/**
		 * This cache Implementation 
		 * Is from YouTube link I saw.
		 * It is mixing between both packages
		 *  <groupId>javax.cache</groupId>
		 *  <groupId>org.ehcache</groupId>
		 */
				
		LOGGER.info(">>>> Eh107CacheConfig1 configuration <<<<");

		// CachingProvider & CacheManager 
		// Implementation is from packages of groupId <groupId>javax.cache</groupId>
		
		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager();

		// This CacheConfiguration is from package <groupId>org.ehcache</groupId>
		CacheConfiguration<Long, Book> cacheConfiguration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(
						Long.class, 
						Book.class, 
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB).build())
				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
				.build();

		javax.cache.configuration.Configuration<Long, Book> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration);

		cacheManager.createCache("booksStore", configuration);
		cacheManager.createCache("personStore", configuration);

		return cacheManager;
	}
}
