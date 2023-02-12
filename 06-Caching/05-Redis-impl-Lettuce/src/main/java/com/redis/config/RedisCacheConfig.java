package com.redis.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedisCacheConfig {

	/**
	 * We have to define 2 beans:
	 * 1. A client (Can be Jedis or Lettuce) ConnectionFactory 
	 * 2. RedisTemplate - which connects to the Redis Server
	 */
	

	/**
	 * Lettuce Connection Factory
	 */
	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory() {

		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName("127.0.0.1");
		redisStandaloneConfiguration.setPort(6379);
		
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);		
		return lettuceConnectionFactory;
	}

	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		
		redisTemplate.setConnectionFactory(lettuceConnectionFactory());
		return redisTemplate;
	}
}
