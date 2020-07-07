package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.TestEntity;
import com.example.demo.vo.TestVO;

public interface TestRepository extends JpaRepository<TestEntity, String>, JpaSpecificationExecutor<TestEntity>{
	@Query("select new com.example.demo.vo.TestVO(t,tt)"
			+ " from TestEntity t "
			+ " left join TestTwoEntity tt on t.id=tt.testEntityId ")
	public List<TestVO> findTestVOAll();
}
