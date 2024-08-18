package com.github.lltal.filler.internal.invocations.resolver.handlers;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoInfo;
import com.github.lltal.filler.internal.invocations.resolver.handlers.impl.ButtonAnnotationHandler;
import com.github.lltal.filler.internal.invocations.resolver.handlers.impl.FilleeFieldAnnotationHandler;
import com.github.lltal.filler.shared.annotation.FilleeField;

import java.lang.reflect.Field;
import java.util.List;

public class FieldAnnotationHandlerRunner {

    private static final List<FieldsAnnotationHandler> fieldsHandlers = List.of(
            new FilleeFieldAnnotationHandler(),
            new ButtonAnnotationHandler());

    public static void run (DtoInfo dtoInfo) {

        dtoInfo.getFields().values()
                        .forEach(fieldInfo -> {
                            fieldsHandlers.forEach(handler -> {
                                handler.handle(fieldInfo);
                            });
                        });
    }
}
