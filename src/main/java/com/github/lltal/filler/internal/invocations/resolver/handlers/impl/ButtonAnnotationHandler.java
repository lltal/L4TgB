package com.github.lltal.filler.internal.invocations.resolver.handlers.impl;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.resolver.handlers.FieldsAnnotationHandler;
import com.github.lltal.filler.shared.annotation.Keyboard;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ButtonAnnotationHandler implements FieldsAnnotationHandler {
    @Override
    public void handle(DtoFieldInfo info) {

        Field field = info.getField();

        if (!field.isAnnotationPresent(Keyboard.class)) {
            return;
        }

        Map<String, String> buttonVsBeanName = new HashMap<>();

        Keyboard keyboard = field.getDeclaredAnnotation(Keyboard.class);
        Arrays.stream(keyboard.buttons())
                .filter(button -> !button.customClickHandler().isEmpty())
                .forEach(button -> buttonVsBeanName.put(button.cbValue(), button.customClickHandler()));

        info.getButtonVsBeanName().putAll(buttonVsBeanName);
    }
}
