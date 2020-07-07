package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.domain.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, String>, JpaSpecificationExecutor<TestEntity>{

}
