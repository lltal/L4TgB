package com.github.lltal.filler.internal.invocations.sender;

import com.github.lltal.filler.internal.invocations.common.ifc.ReturnableMethodExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class SenderInvocationHandler implements InvocationHandler {
    private final Map<Method, ReturnableMethodExecutor> executors;

    public SenderInvocationHandler(
            Map<Method, ReturnableMethodExecutor> executors
    ) {
        this.executors = executors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.getName().equals("toString")) {
            return "SenderProxy";
        }
        return executors.get(method).execute(args);
    }
}
