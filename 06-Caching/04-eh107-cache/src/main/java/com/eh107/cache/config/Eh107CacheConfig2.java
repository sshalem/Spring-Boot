package com.eh107.cache.config;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import org.ehcache.config.CacheRuntimeConfiguration;
import org.ehcache.jsr107.Eh107Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eh107.cache.entity.Book;

//@Configuration
//@EnableCaching
public class Eh107CacheConfig2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Eh107CacheConfig2.class);
	
	@Bean
	@SuppressWarnings("unchecked")
	public CacheManager eh107CacheManager2() {

		LOGGER.info(">>>> Eh107CacheConfig2 configuration <<<<");
		
		CachingProvider provider = Caching.getCachingProvider();  
		CacheManager cacheManager = provider.getCacheManager();
		
		MutableConfiguration<Long, Book> configuration = new MutableConfiguration<>();
		
		configuration.setTypes(Long.class, Book.class);
		
		Cache<Long, Book> cache = cacheManager.createCache("booksStore", configuration);
				
		Eh107Configuration<Long, String> eh107Configuration = cache.getConfiguration(Eh107Configuration.class); 
		
		CacheRuntimeConfiguration<Long, Book> runtimeConfiguration = eh107Configuration.unwrap(CacheRuntimeConfiguration.class);
		
		return cacheManager;
	}
}
