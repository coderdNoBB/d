package com.example.demo.rest;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.Constants;
import com.example.demo.config.JwtProperties;
import com.example.demo.rest.request.LoginRequest;
import com.example.demo.rest.request.RefreshTokenRequest;
import com.example.demo.rest.response.LoginResponse;
import com.example.demo.rest.response.Response;
import com.example.demo.security.jwt.RefreshTokenDTO;
import com.example.demo.security.jwt.TokenProvider;
import com.example.demo.security.jwt.XxAuthenticationToken;
import com.example.demo.util.ObjectMapperUtil;
import com.example.demo.util.ServletIpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@Slf4j
public class LoginResource {

	@Autowired AuthenticationManager authenticationManager;
	@Autowired TokenProvider tokenProvider;
	@Autowired RedisTemplate<String,String> redisTemplate;
	@Autowired JwtProperties jwtProperties;


	@PostMapping(value = "/login")
	public Response login (@RequestBody @Valid LoginRequest request,HttpServletRequest httpRequest) {
		String ip = ServletIpUtil.getClientIpAddress(httpRequest);
		log.info("{} login from {} by ip: {}",request.getAccount(),request.getLoginFrom(),ip);

		//TODO 验证登录请求的账号密码
		if(request.getAccount().equals("d")) {
		
			return Response.success(createToken());
		}else {
			return Response.fail(Constants.LOGIN_FAIL);
		}
	}

	@PostMapping(value = "/refreshToken")
	public Response refreshToken (@RequestBody @Valid RefreshTokenRequest request,HttpServletRequest httpRequest) {
		String ip = ServletIpUtil.getClientIpAddress(httpRequest);
		log.info("{} refreshToken from {} by ip: {}",request.getRefreshToken(), request.getFrom(), ip);
		String refreshTokenFromRedis = this.redisTemplate.opsForValue().get(request.getRefreshToken());
		String message = "";

		try {
			RefreshTokenDTO refreshTokenDTO = ObjectMapperUtil.om.readValue(refreshTokenFromRedis, RefreshTokenDTO.class);
			if(refreshTokenDTO==null) {
				message = "refreshToken expired";
			}else {
				if(refreshTokenDTO.getExpireTime().before(new Date())) {
					message = "refreshToken expired";
				}else {
					return Response.success(createToken()); 
				}

			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return Response.fail(message);
	}

	private LoginResponse createToken() {
		String jwt = buildJwt("d",Constants.ADMIN,false);
		String refreshToken = UUID.randomUUID().toString().replace("-", "");
		DateTime now = DateTime.now();
		DateTime expireTime = now.plusSeconds(jwtProperties.getRefreshTokenExpiredseconds());

		LoginResponse loginResponse=LoginResponse.builder().accessToken(jwt)
				.refreshToken(refreshToken).expiresIn(String.valueOf(jwtProperties.getTokenvalidityinseconds())).build();

		RefreshTokenDTO refreshTokenDTO = RefreshTokenDTO.builder()
				.createTime(now.toDate())
				.expireTime(expireTime.toDate())
				.jwtPlayload("d")
				.build();
		try {
			this.redisTemplate.opsForValue().set(refreshToken, ObjectMapperUtil.om.writeValueAsString(refreshTokenDTO),jwtProperties.getRefreshTokenExpiredseconds(), TimeUnit.SECONDS);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return loginResponse;
	}
	
	/**
	 * 构造JWT
	 * @param id
	 * @param isAdmin
	 * @param rememberMe
	 * @return
	 */
	private String buildJwt(Object id,String isAdmin,boolean rememberMe) {
		XxAuthenticationToken wechatAuthenticationToken = new XxAuthenticationToken(id,isAdmin);
		Authentication authentication = this.authenticationManager.authenticate(wechatAuthenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return tokenProvider.createToken(authentication, rememberMe);
	}
}