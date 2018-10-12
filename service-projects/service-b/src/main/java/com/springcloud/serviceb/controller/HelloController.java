package com.springcloud.serviceb.controller;

import com.springcloud.common.context.UserContext;
import com.springcloud.common.feign.HelloFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

 @Autowired
 private HelloFeignClient helloFeignClient;
    @Value("${test.k2}")
    private String value;
    @GetMapping("/hello")
    public String testHello(){
        String userInfo = UserContext.getUser();
        System.out.println("B   userInfo==========="+userInfo);
        return helloFeignClient.sayHello("小明");
    }

    @GetMapping("/api-service-b/test")
    public String testApi(){
        return value;
    }
}
