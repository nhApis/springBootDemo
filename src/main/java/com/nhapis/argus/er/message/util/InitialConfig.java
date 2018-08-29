package com.nhapis.argus.er.message.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class InitialConfig {

    /**
     * 国际化文件路径
     */
    @Value("${spring.messages.nhapisname}")
    public String nhapisname;

    /**
     * 用于解析消息的策略接口，支持这些消息的参数化和国际化。
     *
     * @return
     */
    @Bean
    public MessageSource nhapisMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        System.out.println("nhapisname====" + nhapisname);
        System.out.println("nhapisname====" + nhapisname);
        messageSource.setBasenames(nhapisname, nhapisname);
        return messageSource;
    }
}
