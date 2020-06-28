package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {

//    @Bean
//    public RedisTemplate<String, Object> redisTemplateObject(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<String,Object>();
//        template.setConnectionFactory(factory);
//        template.setKeySerializer(new GenericToStringSerializer<String>(String.class));
//        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
//        return template;
//    }
}