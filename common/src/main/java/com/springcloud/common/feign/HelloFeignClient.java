package com.springcloud.common.feign;

import com.springcloud.common.web.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.Response;

@FeignClient(serviceId = "service-a",fallback = HelloFeignClientCallBack.class)
public interface HelloFeignClient {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    ResponseResult<String> sayHello(@RequestParam("name")String name);
}
@Component
class HelloFeignClientCallBack implements  HelloFeignClient{

    @Override
    public ResponseResult<String> sayHello(String name) {
        ResponseResult<String>  response = new ResponseResult<String>();
        return response;
    }
}
