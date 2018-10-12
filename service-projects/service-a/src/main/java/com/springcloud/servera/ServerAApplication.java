package com.springcloud.servera;

import com.springcloud.common.filter.ContextFilter;
import com.springcloud.common.filter.UserRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.springcloud.common.feign")
@ComponentScan(basePackages = "com.springcloud")
/*@Import({ContextFilter.class,UserRequestInterceptor.class})*/
public class ServerAApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerAApplication.class, args);
    }
}
