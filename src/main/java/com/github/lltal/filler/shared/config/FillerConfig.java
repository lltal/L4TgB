package com.github.lltal.filler.shared.config;

import com.github.lltal.filler.internal.config.bpp.ButtonBeanPostProcessor;
import com.github.lltal.filler.internal.config.bpp.FieldBeanPostProcessor;
import com.github.lltal.filler.internal.config.listeners.SetterApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.github.lltal.filler"})
public class FillerConfig {

    @Bean
    public ButtonBeanPostProcessor buttonBpp() {
        return new ButtonBeanPostProcessor();
    }

    @Bean
    public FieldBeanPostProcessor fieldBpp() {
        return new FieldBeanPostProcessor();
    }

    @Bean
    public SetterApplicationListener setterApplicationListener() {
        return new SetterApplicationListener();
    }
}
