package com.springcloud.servera.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义注册成功事件
 */
public class RegisterEvent extends ApplicationEvent {
    public RegisterEvent(Object source) {
        super(source);
    }
}
