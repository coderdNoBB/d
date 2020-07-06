package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig  {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String PROD = "PROD";
	@Autowired Environment env;

	@Bean
	public Docket customizeConfig ( ) {
		boolean enable = true;
	     if(Arrays.asList(env.getActiveProfiles()).contains(PROD))
	        	enable = false;
		Docket docket=new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
				.apiInfo(new ApiInfoBuilder()
						.title("swagger_demo")
						.version("1.0.0")
						.build()).enable(enable).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
				.build()
				.securitySchemes(Arrays.asList((apiKey())))
				.securityContexts(Arrays.asList(
						SecurityContext.builder()
						.securityReferences(
								Arrays.asList(SecurityReference.builder()
										.reference("JWT")
										.scopes(new AuthorizationScope[0])
										.build()
										)
								)
						.build()));

		return docket;
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, ApiKeyVehicle.HEADER.getValue()); 
	}

}

