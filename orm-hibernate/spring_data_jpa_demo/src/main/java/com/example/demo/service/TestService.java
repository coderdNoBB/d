package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.TestEntity;
import com.example.demo.domain.TestTwoEntity;

public interface TestService {
	public void batchSave(List<TestEntity> testList);
	public void batchSaveTwo(List<TestTwoEntity> testList);
}
