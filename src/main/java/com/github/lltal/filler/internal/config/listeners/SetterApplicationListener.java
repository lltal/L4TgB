package com.github.lltal.filler.internal.config.listeners;

import com.github.lltal.filler.internal.config.CustomHandlersSetter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

public class SetterApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        CustomHandlersSetter.setCustomHandlersToInvocationHandler();
    }
}
