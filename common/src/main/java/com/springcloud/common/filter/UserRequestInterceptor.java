package com.springcloud.common.filter;

import com.springcloud.common.context.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //这里最好不能直接这么写
        System.out.println("apply===========");
        String userIfo = UserContext.getUser();
        System.out.println("UserRequestInterceptor userIfo==========="+userIfo);
        if(StringUtils.isNotEmpty(userIfo)){
            //如果当前系统中的用户已经存在，那么直接设置head
            requestTemplate.header("user",userIfo);
        }

    }
}
