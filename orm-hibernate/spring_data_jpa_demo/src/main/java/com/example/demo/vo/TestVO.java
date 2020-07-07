package com.example.demo.vo;

import com.example.demo.domain.TestEntity;
import com.example.demo.domain.TestTwoEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestVO {
	private TestEntity testEntity;
	private TestTwoEntity testTwoEntity;
	
	public TestVO(TestEntity testEntity, TestTwoEntity testTwoEntity) {
		super();
		this.testEntity = testEntity;
		this.testTwoEntity = testTwoEntity;
	}
}
