package com.github.lltal.filler.internal.config.bpp;

import com.github.lltal.filler.internal.config.HandlersInfo;
import com.github.lltal.filler.shared.ifc.CustomButtonHandler;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.HashSet;
import java.util.Set;

public class ButtonBeanPostProcessor implements BeanPostProcessor {

    private final Set<String> buttonHandlers = new HashSet<>();

    @Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (CustomButtonHandler.class.isAssignableFrom(bean.getClass())) {
            buttonHandlers.add(beanName);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (buttonHandlers.contains(beanName)) {
            HandlersInfo.getButtonsHandlers().put(beanName, (CustomButtonHandler) bean);
        }

        return bean;
    }
}
