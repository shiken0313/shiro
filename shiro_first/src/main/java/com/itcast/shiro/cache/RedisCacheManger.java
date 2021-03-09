package com.itcast.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class RedisCacheManger implements CacheManager{

	@Override
	public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
	
		return new RedisCache<K,V>(cacheName);
	}

}
