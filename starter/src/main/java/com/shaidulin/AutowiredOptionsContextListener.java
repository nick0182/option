package com.shaidulin;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AutowiredOptionsContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private final ConfigurableListableBeanFactory factory;
    private final Map<String, List<String>> beanNamesMap;

    @Override
    @SneakyThrows
    public void onApplicationEvent(ContextRefreshedEvent event) {
        beanNamesMap.forEach((key, value) -> {
            BeanDefinition beanDefinition = factory.getBeanDefinition(key);
            String originalClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> originalClass = Class.forName(originalClassName);
                for (Field originalClassField : originalClass.getDeclaredFields()) {
                    AutowiredOptions annotation = originalClassField.getAnnotation(AutowiredOptions.class);
                    if (annotation != null && annotation.toAutowire().length != 0) {
                        List<Object> toInject = new ArrayList<>();
                        value.forEach(toInjectBeanName -> {
                            Object toInjectBean = factory.getBean(toInjectBeanName);
                            toInject.add(toInjectBean);
                        });
                        originalClassField.setAccessible(true);
                        Object originalObject = AopProxyUtils.getSingletonTarget(factory.getBean(key));
                        if (originalObject != null) { // that was a proxy
                            ReflectionUtils.setField(originalClassField, originalObject, toInject);
                        } else {
                            ReflectionUtils.setField(originalClassField, factory.getBean(key), toInject);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
