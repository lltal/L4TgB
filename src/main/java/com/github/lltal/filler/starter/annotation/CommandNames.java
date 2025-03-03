package com.github.lltal.filler.starter.annotation;

import com.github.lltal.filler.starter.command.TypeCommand;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandNames {
    String[] value();

    TypeCommand type() default TypeCommand.MESSAGE;
}
