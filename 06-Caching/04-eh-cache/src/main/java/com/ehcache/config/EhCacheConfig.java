package com.ehcache.config;

import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
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

		LOGGER.info(">>>> EhCacheConfig configuration <<<<");

		CacheConfigurationBuilder<Long, Book> cacheConfigurationBuilder = CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, Book.class, ResourcePoolsBuilder.heap(10));

		CacheConfiguration<Long, Book> cacheConfiguration = cacheConfigurationBuilder.build();

		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

		cacheManager.createCache("booksStore", cacheConfiguration);

		return cacheManager;
	}

}
