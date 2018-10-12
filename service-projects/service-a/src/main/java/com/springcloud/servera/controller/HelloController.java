package com.springcloud.servera.controller;

import com.springcloud.common.context.UserContext;
import com.springcloud.common.feign.HelloFeignClient;
import com.springcloud.common.web.ResponseResult;
import com.springcloud.servera.event.RegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController implements HelloFeignClient {
    @Value("${test.k2}")
    private String testValue;
    @Autowired
    private ApplicationContext context;

    @Override
    public ResponseResult<String> sayHello(@RequestParam("name") String name) {
        String userInfo = UserContext.getUser();
        System.out.println("A userInfo==========="+userInfo);
        context.publishEvent(new RegisterEvent(name));
        ResponseResult  result = new ResponseResult();
        result.setData("hello "+name);
        return result;
    }

    @GetMapping("/api-service-a/test")
    public String testApi() {
        System.out.println("testApi===========");
        return testValue;
    }
}

