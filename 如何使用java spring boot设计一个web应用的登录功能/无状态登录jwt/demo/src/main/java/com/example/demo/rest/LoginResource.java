package com.example.demo.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.Constants;
import com.example.demo.rest.request.LoginRequest;
import com.example.demo.rest.response.LoginResponse;
import com.example.demo.rest.response.Response;
import com.example.demo.security.jwt.TokenProvider;
import com.example.demo.security.jwt.XxAuthenticationToken;
import com.example.demo.util.ServletIpUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RestController
@Slf4j
public class LoginResource {

	@Autowired AuthenticationManager authenticationManager;
	@Autowired TokenProvider tokenProvider;
	
	@PostMapping(value = "/login")
	public Response login (@RequestBody @Valid LoginRequest request,HttpServletRequest httpRequest) {
		String ip = ServletIpUtil.getClientIpAddress(httpRequest);
		log.info("{} login from {} by ip: {}",request.getAccount(),request.getLoginFrom(),ip);

		//TODO 验证登录请求的账号密码
		if(request.getAccount().equals("d")) {
			String jwtToken = buildJwt("d",Constants.ADMIN,false);
			LoginResponse loginResponse=LoginResponse.builder().jwtToken(jwtToken)
					.userName("").build();
			return Response.success(loginResponse);
		}else {
			return Response.fail(Constants.LOGIN_FAIL);
		}
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