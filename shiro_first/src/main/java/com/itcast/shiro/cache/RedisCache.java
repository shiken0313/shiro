package com.itcast.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.itcast.utils.ApplicationContextUtils;

@Component
public class RedisCache<K, V> implements Cache<K, V> {

	@Autowired
	private RedisTemplate redisTemplate;

	private String cacheName;

	public RedisCache(String cacheName)
	{
		this.cacheName = cacheName;
	}

	public RedisCache()
	{

	}

	@Override
	public void clear() throws CacheException {

	}

	@Override
	public V get(K k) throws CacheException {

		return (V) getRedisTemplate().opsForHash().get(this.cacheName, k.toString());
	}

	@Override
	public Set<K> keys() {

		return null;
	}

	@Override
	public V put(K k, V v) throws CacheException {

		getRedisTemplate().opsForHash().put(this.cacheName, k.toString(), v);
		return null;
	}

	@Override
	public V remove(K k) throws CacheException {

		return null;
	}

	@Override
	public int size() {

		return 0;
	}

	@Override
	public Collection<V> values() {

		return null;
	}
	
	public RedisTemplate getRedisTemplate() {
		RedisTemplate redisTemplate = (RedisTemplate)ApplicationContextUtils.getBean("redisTemplate");
		//String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //key採用String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //hash的key也採用String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        
        return redisTemplate;
	}

}
