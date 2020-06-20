package com.example.d.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession 
public class HttpSessionRedisConfig {

	@Bean
	public RedissonClient create() {
		return Redisson.create();
	}

}