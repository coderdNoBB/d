package com.example.demo.rest.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SomeRequest {
	@ApiModelProperty(value = "手机号")
	private String mobile;
	
	@ApiModelProperty(value = "内容")
	private String content;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createtime;

	private String email;  //邮箱
}
