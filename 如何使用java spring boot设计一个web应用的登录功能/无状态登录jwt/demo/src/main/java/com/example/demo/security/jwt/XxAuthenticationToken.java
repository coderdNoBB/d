package com.example.demo.security.jwt;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
/**
 * @description 项目的 的JWT TOKEN
 * @author coderd
 *
 */
public class XxAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 8502696492677211950L;
	private Object id;
	private Object accountType;

	public XxAuthenticationToken(Object id, Object accountType ,Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.id = id;
		this.accountType = accountType;
		super.setAuthenticated(true);
	}

	public XxAuthenticationToken(Object id,Object accountType) {
		super(null);
		this.id = id;
		this.accountType = accountType;
		setAuthenticated(false);
	}



	@Override
	public Object getCredentials() {
		return this.accountType;
	}

	@Override
	public Object getPrincipal() {
		return this.id;
	}

}
