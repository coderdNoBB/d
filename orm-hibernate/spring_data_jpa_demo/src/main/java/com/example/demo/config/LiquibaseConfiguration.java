package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfiguration {
	@Autowired
	DataSource dataSource;

	/**
	 *  XX模块Liquibase   
	 */
	@Bean
	public SpringLiquibase xxLiquibase() {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:db/master.xml");
		liquibase.setDataSource(dataSource);
		liquibase.setShouldRun(true);
		liquibase.setResourceLoader(new DefaultResourceLoader());
		return liquibase;
	}

}