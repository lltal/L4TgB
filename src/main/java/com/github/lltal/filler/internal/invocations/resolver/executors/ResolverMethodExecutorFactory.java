package com.github.lltal.filler.internal.invocations.resolver.executors;

import com.github.lltal.filler.internal.invocations.common.ifc.MethodExecutor;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.resolver.executors.impl.ResolveExecutor;
import com.github.lltal.filler.shared.ifc.AbstractResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResolverMethodExecutorFactory {

    public static Map<Method, MethodExecutor> getExecutors(
            Map<Integer, DtoFieldInfo> fields
    ) {
        Map<Method, MethodExecutor> executors = new HashMap<>();

        Arrays.stream(AbstractResolver.class.getDeclaredMethods())
                .forEach(method -> selectHandler(method, executors, fields));

        return executors;
    }

    private static void selectHandler(
            Method method,
            Map<Method, MethodExecutor> executors,
            Map<Integer, DtoFieldInfo> fields
    ) {
        switch (method.getName()) {
            case "resolve" -> {
                executors.put(method, new ResolveExecutor(fields));
            }
        }
    }

}
