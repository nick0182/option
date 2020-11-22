package com.shaidulin;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AutowiredOptionsBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, List<String>> beanNamesMap;

    private final ApplicationContext context;

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            AutowiredOptions annotation = declaredField.getAnnotation(AutowiredOptions.class);
            if (annotation != null && annotation.toAutowire().length != 0) {
                Class<?>[] toAutowireClasses = annotation.toAutowire();

                beanNamesMap.put(beanName, new ArrayList<>());
                for (Class<?> toAutowireClass : toAutowireClasses) {
                    String[] beanNamesForType = context.getBeanNamesForType(toAutowireClass);
                    if (beanNamesForType.length != 1) {
                        throw new NoUniqueBeanDefinitionException(toAutowireClass, beanNamesForType.length,
                                "There are " + beanNamesForType.length + " beans for type " + toAutowireClass);
                    } else {
                        beanNamesMap.get(beanName).add(beanNamesForType[0]);
                    }
                }
            }
        }
        return bean;
    }
}
