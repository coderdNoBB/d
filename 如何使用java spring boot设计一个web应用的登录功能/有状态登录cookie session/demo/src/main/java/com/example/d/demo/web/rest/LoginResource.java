package com.example.d.demo.web.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class LoginResource {
    @GetMapping(value = "/")
    public String demo() {
        return "Spring Boot + Spring Security Configuration Demo";
    }
}
