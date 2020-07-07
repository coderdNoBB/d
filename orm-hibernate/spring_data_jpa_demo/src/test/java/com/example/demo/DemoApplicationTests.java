package com.example.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.TestEntity;
import com.example.demo.domain.TestTwoEntity;
import com.example.demo.repository.TestRepository;
import com.example.demo.repository.TestTwoRepository;
import com.example.demo.service.TestService;
import com.example.demo.vo.TestVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class DemoApplicationTests {
	@Autowired TestService testService;
	@Autowired TestRepository testRepository;
	@Autowired TestTwoRepository testTwoRepository;
	@Test
	void batchSaveAndQueryMoreObject() {
		List<TestEntity> list=new ArrayList<>();
		List<TestTwoEntity> twoList=new ArrayList<>();
		for(int i=0;i<60;i++) {
			String testEntityId = UUID.randomUUID().toString().replace("-", "");
			list.add(TestEntity.builder()
					.id(testEntityId)
					.content(UUID.randomUUID().toString())
					.type(1)
					.createTime(new Date())
					.remark(UUID.randomUUID().toString())
					.build());

			twoList.add(TestTwoEntity.builder()
					.id(UUID.randomUUID().toString().replace("-", ""))
					.content(UUID.randomUUID().toString())
					.type(1)
					.createTime(new Date())
					.remark(UUID.randomUUID().toString())
					.testEntityId(testEntityId)
					.build());
		}

		testService.batchSave(list);
		testService.batchSaveTwo(twoList);
		list.forEach(item->log.info(item.toString()));
		twoList.forEach(item->log.info(item.toString()));
		log.info("count: {}",testRepository.count());
		// moreObject query 
		List<TestVO> listTestVO = testRepository.findTestVOAll();
		listTestVO.forEach(item->log.info(item.toString()));
	}

}
