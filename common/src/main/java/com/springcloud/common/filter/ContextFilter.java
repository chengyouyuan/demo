package com.springcloud.common.filter;

import com.springcloud.common.context.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ContextFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = requestAttributes.getRequest();
        String  userInfo = httpServletRequest.getHeader("user");
        if(StringUtils.isNotEmpty(userInfo)){
            //有进行赋值，没有清空
            UserContext.setUser(userInfo);
        }else{
            UserContext.remove();
        }
        System.out.println("doFilter===========");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
