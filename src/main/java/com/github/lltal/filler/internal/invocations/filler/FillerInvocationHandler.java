package com.github.lltal.filler.internal.invocations.filler;

import com.github.lltal.filler.internal.invocations.common.ifc.MethodExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class FillerInvocationHandler implements InvocationHandler {
    private final Map<Method, MethodExecutor> executors;

    public FillerInvocationHandler(
            Map<Method, MethodExecutor> executors
    ) {
        this.executors = executors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.getName().equals("toString")) {
            return "FillerProxy";
        }
        executors.get(method).execute(args);

        return null;
    }
}
