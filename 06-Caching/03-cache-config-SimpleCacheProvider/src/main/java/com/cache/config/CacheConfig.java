package com.cache.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cache.Application;

@Configuration
@EnableCaching
public class CacheConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(
				Arrays.asList(
						new ConcurrentMapCache("booksStore"),
						new ConcurrentMapCache("myDemoCache")
						));

		LOGGER.info(" ConcurrentMapCache ---->  booksStore cache, myDemoCache");

		return cacheManager;
	}
}
