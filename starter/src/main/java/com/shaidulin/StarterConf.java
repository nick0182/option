package com.shaidulin;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarterConf {
    @Bean
    public BeanPostProcessor autowiredOptionsBeanPostProcessor(ApplicationContext context) {
        return new AutowiredOptionsBeanPostProcessor(context);
    }
}
