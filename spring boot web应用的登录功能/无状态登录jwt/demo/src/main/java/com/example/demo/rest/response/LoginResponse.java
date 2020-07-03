package com.example.demo.rest.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	@ApiModelProperty(value = "tokenType")
	@Builder.Default
	private String tokenType = "bearer";
	
	@ApiModelProperty(value = "accessToken")
	private String accessToken;

}
