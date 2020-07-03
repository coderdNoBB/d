package com.example.demo.rest.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadResponse {

	private List<String> details;
	private String message;
}
