package com.github.lltal.filler.internal.sorter;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoInfo;
import com.github.lltal.filler.internal.invocations.resolver.handlers.FieldAnnotationHandlerRunner;
import com.github.lltal.filler.shared.annotation.FilleeField;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class FieldsInfoCreator {
    public static void createFieldsInfo(DtoInfo dtoInfo) {
        Map<Integer, DtoFieldInfo> fields = new HashMap<>();

        AtomicInteger fieldsCounter = new AtomicInteger();

        Arrays.stream(dtoInfo.getDto().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(FilleeField.class))
                .sorted(FieldsInfoCreator::compare)
                .map(DtoFieldInfo::new)
                .forEach(fieldInfo -> {
                    fields.put(fieldsCounter.getAndIncrement(), fieldInfo);
                });

        dtoInfo.setFields(fields);
    }

    private static int compare(Field f1, Field f2) {
        return f2.getDeclaredAnnotation(FilleeField.class).order() -
                f1.getDeclaredAnnotation(FilleeField.class).order();
    }
}
