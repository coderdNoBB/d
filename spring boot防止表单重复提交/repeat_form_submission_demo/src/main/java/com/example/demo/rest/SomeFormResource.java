package com.example.demo.rest;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.request.SomeFormRequest;
import com.example.demo.rest.response.Response;

@RestController
public class SomeFormResource {
	
	@PostMapping(value = "/submit")
	public Response submit (@RequestBody SomeFormRequest request) {
		return Response.success(request.getContent());
	}
	
	@GetMapping(value = "/uuid")
	public Response uuid () {
		String UUId = UUID.randomUUID().toString().replace("-", "");
		return Response.success(UUId);
	}
}
