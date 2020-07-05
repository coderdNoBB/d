package com.example.demo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.FormSubmitInterceptor;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new FormSubmitInterceptor(redisTemplate)).addPathPatterns("/submit");
	}

}
