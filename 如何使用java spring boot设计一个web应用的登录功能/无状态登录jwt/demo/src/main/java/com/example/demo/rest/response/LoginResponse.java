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
	@ApiModelProperty(value = "jwtToken")
	private String jwtToken;
	@ApiModelProperty(value = "用户页面显示名字")
	private String userName;
	
	public void setMobileFourStar() {
		this.userName=this.userName.substring(0,3)+"****"+this.userName.substring(7,11);
	}
}
