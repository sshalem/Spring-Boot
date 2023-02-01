package com.ehcache.config;

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

import com.ehcache.entity.Book;

@Configuration
@EnableCaching
public class EhCacheConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(EhCacheConfig.class);

	@Bean
	public CacheManager ehCacheManager() {

		LOGGER.info(">>>> EhCache configuration <<<<");
		CacheConfiguration<Long, Book> cachecConfig = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(
						Long.class, 
						Book.class,
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(10, MemoryUnit.MB).build()
						)
				.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
				.build();

		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager();

		javax.cache.configuration.Configuration<Long, Book> configuration = Eh107Configuration
				.fromEhcacheCacheConfiguration(cachecConfig);
		cacheManager.createCache("booksStore", configuration);

		return cacheManager;
	}

}
