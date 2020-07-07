package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.domain.TestTwoEntity;

public interface TestTwoRepository extends JpaRepository<TestTwoEntity, String>, JpaSpecificationExecutor<TestTwoEntity>{

}
