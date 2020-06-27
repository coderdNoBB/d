/*
 *  THIS FILE IS PART OF C8software PROJECT
 *  Copyright (c) 2011 - 2018 C8.Co.Ltd. All rights reserved.
 *  Mr.Yellow (www.c8software.com) 18-12-29 下午7:19
 *
 */

package com.example.demo.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "jwt")

@Component
public class JwtProperties {
	private   String key;

	private  Long tokenvalidityinseconds;

	private  Long tokenvalidityinsecondsforrememberme;

	private String  headAuthorkey;


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getTokenvalidityinseconds() {
		return tokenvalidityinseconds;
	}

	public void setTokenvalidityinseconds(Long tokenvalidityinseconds) {
		this.tokenvalidityinseconds = tokenvalidityinseconds;
	}

	public Long getTokenvalidityinsecondsforrememberme() {
		return tokenvalidityinsecondsforrememberme;
	}

	public void setTokenvalidityinsecondsforrememberme(Long tokenvalidityinsecondsforrememberme) {
		this.tokenvalidityinsecondsforrememberme = tokenvalidityinsecondsforrememberme;
	}

	public String getHeadAuthorkey() {
		return headAuthorkey;
	}

	public void setHeadAuthorkey(String headAuthorkey) {
		this.headAuthorkey = headAuthorkey;
	}
}


