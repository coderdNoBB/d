package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.TestEntity;

public interface TestService {
	public void batchSave(List<TestEntity> testList);
}
