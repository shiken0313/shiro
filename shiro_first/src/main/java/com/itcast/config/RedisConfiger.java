package com.itcast.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;



//@Configuration
public class RedisConfiger {

	
	 //編寫我們自己的redisTemplate
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //為了開發方便 一般使用<String, Object>
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        
        //String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //key採用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //hash的key也採用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
     

        return template;
    }

 	
}
