package com.example.demo.interceptor;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.exception.FromSubmitException;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * @description 表单重复提交拦截器
 * @author coderd
 *
 */
public class FormSubmitInterceptor extends HandlerInterceptorAdapter {
	private static String headerName = "formId";
	private RedisTemplate<String, String> redisTemplate;

	public FormSubmitInterceptor(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String formId = request.getHeader(headerName);
		if(!StringUtils.isBlank(formId)) {
			if(redisTemplate.hasKey(formId)) {
				throw new FromSubmitException();
			}else {
				redisTemplate.opsForValue().setIfAbsent(formId,"",5,TimeUnit.SECONDS);
			}
		}else {
			throw new FromSubmitException();
		}
		return super.preHandle(request, response, handler);
	}

}
