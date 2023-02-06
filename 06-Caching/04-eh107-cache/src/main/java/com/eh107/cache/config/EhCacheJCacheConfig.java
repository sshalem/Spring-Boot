package com.eh107.cache.config;

import java.time.Duration;

import javax.cache.Caching;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;

import com.eh107.cache.entity.Book;

//@Configuration
//@EnableCaching
public class EhCacheJCacheConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(EhCacheJCacheConfig.class);

	/**
	 * CacheManager from --> org.springframework.cache.CacheManager;
	 */
	@Bean
	public CacheManager ehJCacheCacheManager() {

		LOGGER.info(">>>> JCacheConfig configuration <<<<");

		CacheConfiguration<Long, Book> cacheConfiguration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(
						Long.class, 
						Book.class, 
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB).build())
				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
				.build();
		
        javax.cache.CacheManager cacheManager = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider").getCacheManager();
        
        javax.cache.configuration.Configuration<Long, Book> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration);
		cacheManager.createCache("booksStore", configuration);
		cacheManager.createCache("personStore", configuration);
		
		return new JCacheCacheManager(cacheManager);			
	}

}
