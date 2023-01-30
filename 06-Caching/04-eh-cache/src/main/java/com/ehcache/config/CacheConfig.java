package com.ehcache.config;

import java.time.Duration;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

    @Bean
    public CacheManager ehCacheManager() {
//	CacheConfiguration<Long, String> cacheConfiguration = CacheConfigurationBuilder
//		.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)).build();

	CacheConfiguration<Long, String> cacheConfiguration = CacheConfigurationBuilder
		.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(100))
		.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20)))
		.build();
	return null;
    }
}
