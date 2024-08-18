package com.github.lltal.filler.internal.invocations.filler.executors;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.common.ifc.MethodExecutor;
import com.github.lltal.filler.internal.invocations.filler.executors.impl.FillExecutor;
import com.github.lltal.filler.shared.ifc.AbstractFiller;
import com.github.lltal.filler.shared.ifc.AbstractSender;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FillerExecutorMethodFactory {

    public static Map<Method, MethodExecutor> getExecutors(
            Map<Integer, DtoFieldInfo> fields
    ) {
        Map<Method, MethodExecutor> executors = new HashMap<>();

        Arrays.stream(AbstractFiller.class.getDeclaredMethods())
                .forEach(method -> selectHandler(method, executors, fields));

        return executors;
    }

    private static void selectHandler(
            Method method,
            Map<Method, MethodExecutor> executors,
            Map<Integer, DtoFieldInfo> fields
    ) {
        switch (method.getName()) {
            case "fill" -> {
                executors.put(method, new FillExecutor(fields));
            }
        }
    }

}
