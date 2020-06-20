package com.example.d.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import com.example.d.demo.security.handler.AjaxAuthenticationFailureHandler;
import com.example.d.demo.security.handler.AjaxAuthenticationSuccessHandler;
import com.example.d.demo.security.handler.AjaxLogoutSuccessHandler;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	//TODO 又需要自己配置，跨域Filter
	private CorsFilter corsFilter = new CorsFilter(new UrlBasedCorsConfigurationSource());
	private final SecurityProblemSupport problemSupport;

	public SecurityConfiguration(SecurityProblemSupport problemSupport) {
		this.problemSupport = problemSupport;
	}


	@Bean
	public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
		return new AjaxAuthenticationSuccessHandler();
	}

	@Bean
	public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
		return new AjaxAuthenticationFailureHandler();
	}

	@Bean
	public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler() {
		return new AjaxLogoutSuccessHandler();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) {
		web
		.ignoring()
		.antMatchers(HttpMethod.OPTIONS, "/**")
		.antMatchers("/app/**/*.{js,html}")
		.antMatchers("/i18n/**")
		.antMatchers("/content/**")
		.antMatchers("/h2-console/**")
		.antMatchers("/swagger-ui/index.html")
		.antMatchers("/test/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
		.csrf()
		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and()
		.addFilterBefore(corsFilter, CsrfFilter.class)
		.exceptionHandling()
		.authenticationEntryPoint(problemSupport)
		.accessDeniedHandler(problemSupport)
		.and()
		//TODO rememberMe 相关可以参考jhipster手脚架里的。PersistentTokenRememberMeServices.java
		//		.rememberMe() 
		//		.rememberMeServices(rememberMeServices)
		//		.rememberMeParameter("remember-me")
		//		.key("asavekey")
		//		.and()
		.formLogin()
		.loginProcessingUrl("/api/authentication")
		.successHandler(ajaxAuthenticationSuccessHandler())
		.failureHandler(ajaxAuthenticationFailureHandler())
		.permitAll()
		.and()
		.logout()
		.logoutUrl("/api/logout")
		.logoutSuccessHandler(ajaxLogoutSuccessHandler())
		.permitAll()
		.and()
		.headers()
		.contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
		.and()
		.referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
		.and()
		.featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; speaker 'none'; fullscreen 'self'; payment 'none'")
		.and()
		.frameOptions()
		.deny()
		.and()
		.authorizeRequests()
		.antMatchers("/api/common/**").permitAll();
	}
}
