package com.example.demo.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.rest.request.SomeRequest;
import com.example.demo.rest.response.Response;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@RestController
public class SwaggerDemoResource {
	@ApiOperation(value = "swagger_demo", notes = "", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	@PostMapping(value = "/swagger")
	public Response submit (@RequestBody SomeRequest request) {
		return Response.success(request.getContent());
	}
}
