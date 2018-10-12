package com.springcloud.servera.service;

import com.springcloud.servera.event.RegisterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImp implements ApplicationListener<RegisterEvent> {
    Logger log = LoggerFactory.getLogger(SendEmailServiceImp.class);
    @Override
    public void onApplicationEvent(RegisterEvent registerEvent) {
        log.info("接受到注册完毕的事件，准备给{}发送邮件",registerEvent.getSource());
    }
}
