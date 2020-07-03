package com.example.demo.security.jwt;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDTO {
	private Date createTime;
	private Date expireTime;
	@ApiModelProperty("保存一下能构建jwt playload的数据")
	private String jwtPlayload;
}
