package com.eh107.cache.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomCacheEventListener implements CacheEventListener<Object, Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomCacheEventListener.class);

	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
		LOGGER.info("{}: key={}, old={}, new={}", event.getType(), event.getKey(), event.getOldValue(),	event.getNewValue());
	}
}
