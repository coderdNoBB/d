package com.example.demo.rest.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RefreshTokenRequest {
	@ApiModelProperty(value = "refreshToken")
	@NotBlank(message="refreshToken 不能为空")
	public String refreshToken;
	
	@ApiModelProperty(value = "from")
	public String from;
}
