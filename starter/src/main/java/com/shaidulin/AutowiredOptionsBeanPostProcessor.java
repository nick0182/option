package com.shaidulin;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AutowiredOptionsBeanPostProcessor implements BeanPostProcessor {

    private final ApplicationContext context;

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            AutowiredOptions annotation = declaredField.getAnnotation(AutowiredOptions.class);
            if (annotation != null && annotation.toAutowire().length != 0) {
                List<Object> toInject = new ArrayList<>();
                declaredField.setAccessible(true);
                ReflectionUtils.setField(declaredField, bean, toInject);
                Class<?>[] toAutowireClasses = annotation.toAutowire();
                for (Class<?> toAutowireClass : toAutowireClasses) {
                    toInject.add(context.getBean(toAutowireClass));
                }
            }
        }
        return bean;
    }
}
