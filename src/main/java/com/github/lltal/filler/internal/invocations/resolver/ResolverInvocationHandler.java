package com.github.lltal.filler.internal.invocations.resolver;

import com.github.lltal.filler.internal.invocations.common.ifc.MethodExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class ResolverInvocationHandler implements InvocationHandler {

    private final Map<Method, MethodExecutor> executors;

    public ResolverInvocationHandler(
            Map<Method, MethodExecutor> executors
    ) {
        this.executors = executors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("toString")) {
            return "ResolverProxy";
        }

        executors.get(method).execute(args);

        return null;
    }
}
