package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity {
	@Id
	@GeneratedValue(generator = "generator")    
	@GenericGenerator(name = "generator", strategy = "uuid") 
	private String id;

	private String content;

	private String remark;

	private Integer type;
	
	private Date createTime;
}
