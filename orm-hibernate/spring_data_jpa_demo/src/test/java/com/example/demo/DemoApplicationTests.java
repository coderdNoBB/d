package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.TestEntity;
import com.example.demo.repository.TestRepository;
import com.example.demo.service.TestService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class DemoApplicationTests {
	@Autowired TestService testService;
	@Autowired TestRepository testRepository;

	@Test
	void batchSave() {
		List<TestEntity> list=new ArrayList<>();
		for(int i=0;i<1000000;i++) {
			list.add(TestEntity.builder()
					.content(UUID.randomUUID().toString())
					.type(1)
					.createTime(new Date())
					.remark(UUID.randomUUID().toString())
					.build());
		}
		testService.batchSave(list);
		log.info("count: {}",testRepository.count());
	}

}
