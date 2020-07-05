package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.rest.response.Response;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static String msessage = "重复提交了或者缺少formId header";
	
	@ExceptionHandler(FromSubmitException.class) 
	@ResponseBody
	public Response customHandler(FromSubmitException e){
		return Response.fail(msessage);
	}
}