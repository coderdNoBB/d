package com.example.demo.rest.request;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SomeRequest {
	@ApiModelProperty(value = "手机号")
	@Pattern(regexp = "1\\d{10}", message = "手机号不正确")
	private String mobile;
	
	@ApiModelProperty(value = "内容")
	@NotEmpty(message="content不能为空")
	private String content;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message="时间格式为：yyyy-MM-dd")
	private Date createtime;

	@Email(message="邮箱格式不匹配")
	private String email;  //邮箱
	
}
