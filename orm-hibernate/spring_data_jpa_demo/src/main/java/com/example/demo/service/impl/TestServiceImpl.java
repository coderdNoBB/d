package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.TestEntity;
import com.example.demo.domain.TestTwoEntity;
import com.example.demo.repository.TestRepository;
import com.example.demo.repository.TestTwoRepository;
import com.example.demo.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	@Autowired TestRepository testRepository;
	@Autowired TestTwoRepository testTwoRepository;
	
	@Override
	public void batchSave(List<TestEntity> testList) {
		testRepository.saveAll(testList);
	}
	
	@Override
	public void batchSaveTwo(List<TestTwoEntity> testList) {
		testTwoRepository.saveAll(testList);
	}

}
