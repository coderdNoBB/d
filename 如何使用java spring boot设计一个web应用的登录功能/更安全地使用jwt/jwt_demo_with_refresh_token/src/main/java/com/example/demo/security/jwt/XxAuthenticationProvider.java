package com.example.demo.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @description 温测2.0 微信授权登录的JWT TOKEN的认证提供者
 * @author coderd
 *
 */
@Component
public class XxAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(authentication.getCredentials().toString()));
		return new XxAuthenticationToken(
				authentication.getPrincipal(),
				authentication.getCredentials(),
				grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (XxAuthenticationToken.class
				.isAssignableFrom(authentication));
	}

}
