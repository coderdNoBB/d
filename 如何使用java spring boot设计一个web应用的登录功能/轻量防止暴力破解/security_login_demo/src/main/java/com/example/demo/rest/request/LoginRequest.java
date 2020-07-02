package com.example.demo.rest.request;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequest {
	@ApiModelProperty(value = "手机号")
	@Pattern(regexp = "1\\d{10}", message = "手机号不正确")
	public String mobile;
	
	@ApiModelProperty(value = "密码")
	public String password;
	
	@ApiModelProperty(value = "账号密码")
	public String account;
	
	@ApiModelProperty(value = "验证码")
	public String vcode;
	
	@ApiModelProperty(value = "登录来源")
	public String loginFrom;
}
