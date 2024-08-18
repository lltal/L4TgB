package com.github.lltal.filler.internal.invocations.resolver.handlers.impl;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.filler.handlers.impl.FilleeFieldHandlerImpl;
import com.github.lltal.filler.internal.invocations.resolver.handlers.FieldsAnnotationHandler;
import com.github.lltal.filler.shared.annotation.FilleeField;

import java.lang.reflect.Field;

public class FilleeFieldAnnotationHandler implements FieldsAnnotationHandler {

    @Override
    public void handle(DtoFieldInfo info) {
        Field field = info.getField();
        info.setFilleeHandler(new FilleeFieldHandlerImpl(field));

        String beanName = field
                .getDeclaredAnnotation(FilleeField.class)
                .customFillHandler();

        if(beanName.isEmpty()) {
            return;
        }

        info.setFilleeHandlerBeanName(beanName);
    }
}
