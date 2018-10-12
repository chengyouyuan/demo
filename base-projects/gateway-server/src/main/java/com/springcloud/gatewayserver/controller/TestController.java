package com.springcloud.gatewayserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${test.k1}")
    private String v1;
    @GetMapping("/test")
    public  String  test(){
        return v1;
    }
}
