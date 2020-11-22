package com.shaidulin;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class StarterConf {

    @Bean
    public Map<String, List<String>> beanNamesMap() {
        return new HashMap<>();
    }

    @Bean
    public BeanPostProcessor autowiredOptionsBeanPostProcessor(Map<String, List<String>> beanNamesMap,
                                                               ApplicationContext context) {
        return new AutowiredOptionsBeanPostProcessor(beanNamesMap, context);
    }

    @Bean
    public ApplicationListener<ContextRefreshedEvent> autowiredOptionsContextListener(
            ConfigurableListableBeanFactory factory, Map<String, List<String>> beanNamesMap) {
        return new AutowiredOptionsContextListener(factory, beanNamesMap);
    }
}
