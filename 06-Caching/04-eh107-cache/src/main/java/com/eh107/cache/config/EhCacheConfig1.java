package com.eh107.cache.config;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableCaching
public class EhCacheConfig1 {

	private static final Logger LOGGER = LoggerFactory.getLogger(EhCacheConfig1.class);

	@Bean
	public CacheManager ehCacheManager() {

		LOGGER.info(">>>> EhCacheConfig1 configuration <<<<");

		CacheManager cacheManager = CacheManagerBuilder
				.newCacheManagerBuilder()
				.withCache("booksStore", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
				.build(true);	

		return cacheManager;			
	}

}
