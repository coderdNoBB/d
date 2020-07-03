package com.example.demo.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
	private String key;
	private Long tokenvalidityinseconds;
	private Long tokenvalidityinsecondsforrememberme;
	private Integer refreshTokenExpiredseconds;
}


