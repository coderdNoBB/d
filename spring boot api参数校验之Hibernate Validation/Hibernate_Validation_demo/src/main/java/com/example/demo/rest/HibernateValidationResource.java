package com.example.demo.rest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.request.SomeRequest;
import com.example.demo.rest.response.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HibernateValidationResource {

	@PostMapping(value = "/test")
	public Response test (@RequestBody @Valid SomeRequest request) {
		log.info(request.toString());
		return Response.success(request.toString());
	}

}
