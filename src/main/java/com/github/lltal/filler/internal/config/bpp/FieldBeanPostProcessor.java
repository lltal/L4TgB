package com.github.lltal.filler.internal.config.bpp;

import com.github.lltal.filler.internal.config.HandlersInfo;
import com.github.lltal.filler.shared.ifc.CustomFilleeHandler;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public class FieldBeanPostProcessor implements BeanPostProcessor {
    private final Set<String> fieldHandlers = new HashSet<>();

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (CustomFilleeHandler.class.isAssignableFrom(bean.getClass())) {
            fieldHandlers.add(beanName);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (fieldHandlers.contains(beanName)) {
            HandlersInfo.getFieldsHandlers().put(beanName, (CustomFilleeHandler) bean);
        }

        return bean;
    }
}
