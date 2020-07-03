package com.example.demo.util;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.config.Constants;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SimpleLoginFilter {
	@Autowired RedisTemplate<String,Integer> redisTemplate;
	@Autowired RedisTemplate<String,String> stringRedisTemplate;
	private static int ipRequestTimesLimit = 500;
	private static int accountRequestTimesLimit = 5;
	private static String userAgent = "user-agent";
	/**
	 * @description 返回false 就进行一下验证码 拼图选图片验证一下是否机器人
	 * @param account
	 * @param loginFrom
	 * @param httpRequest
	 * @return
	 */
	public boolean canLogin(String account,String loginFrom,HttpServletRequest httpRequest) {
		boolean canLogin = false;
		String ip = ServletIpUtil.getClientIpAddress(httpRequest);
		log.info("{} login from {} by ip: {}",account, loginFrom ,ip);
		String userAgentFromHeader = httpRequest.getHeader(userAgent);
		if(isIpCounterOK(ip) && isAccountOK(account) && isUserAgentUsually(account,userAgentFromHeader)) {
			canLogin = true;
		}	
		return canLogin;
	}

	private boolean isIpCounterOK(String ip) {
		Integer ipRequestTimes = redisTemplate.opsForValue().get(ip);
		if(ipRequestTimes!=null) {
			if(ipRequestTimes<ipRequestTimesLimit) {
				redisTemplate.opsForValue().increment(ip);
				return true;
			}else {
				log.info("ip {} has login {} times",ip,ipRequestTimes);
				return false;
			}
		}else {
			redisTemplate.opsForValue().set(ip, 1,1,TimeUnit.HOURS);
			return true;
		}
	}

	private boolean isAccountOK(String account) {
		Integer accountRequestTimes = redisTemplate.opsForValue().get(Constants.KEY_ACCOUNT+account);
		if(accountRequestTimes!=null) {
			if(accountRequestTimes<accountRequestTimesLimit) {
				redisTemplate.opsForValue().increment(Constants.KEY_ACCOUNT+account);
				return true;
			}else {
				log.info("account {} has login {} times",account,accountRequestTimes);
				return false;
			}
		}else {
			redisTemplate.opsForValue().set(Constants.KEY_ACCOUNT+account, 1,1,TimeUnit.HOURS);
			return true;
		}
	}

	// 这个可以改成数据库里保存比较的。历史表什么的
	private boolean isUserAgentUsually(String account,String userAgent) {
		String userAgentFromRedis = stringRedisTemplate.opsForValue().get(Constants.KEY_UA+account);
		if(userAgentFromRedis!=null) {
			if(userAgentFromRedis.equals(userAgent)) {
				return true;
			}else {
				log.info("{} not login in the usually client");
				return false;
			}
		}else {
			stringRedisTemplate.opsForValue().set(Constants.KEY_UA+account, userAgent,1,TimeUnit.HOURS);
			return true;
		}
	}
}
