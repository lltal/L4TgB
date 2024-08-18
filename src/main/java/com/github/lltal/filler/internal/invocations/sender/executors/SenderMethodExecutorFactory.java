package com.github.lltal.filler.internal.invocations.sender.executors;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.common.ifc.ReturnableMethodExecutor;
import com.github.lltal.filler.internal.invocations.sender.executors.impl.CloseCbExecutor;
import com.github.lltal.filler.internal.invocations.sender.executors.impl.NextMessageExecutor;
import com.github.lltal.filler.internal.invocations.sender.executors.impl.NextMessageWithoutKeyboardExecutor;
import com.github.lltal.filler.shared.ifc.AbstractSender;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SenderMethodExecutorFactory {
    public static Map<Method, ReturnableMethodExecutor> getExecutors(
            Map<Integer, DtoFieldInfo> fields
    ) {
        Map<Method, ReturnableMethodExecutor> executors = new HashMap<>();

        Arrays.stream(AbstractSender.class.getDeclaredMethods())
                .forEach(method -> selectExecutor(method, executors, fields));

        return executors;
    }

    private static void selectExecutor(
            Method method,
            Map<Method, ReturnableMethodExecutor> executors,
            Map<Integer, DtoFieldInfo> fields
    ) {
        switch (method.getName()) {
            case "getNextMessage" -> {
                executors.put(method, new NextMessageExecutor(fields));
            }
            case "getNextMessageWithoutKeyboard" -> {
                executors.put(method, new NextMessageWithoutKeyboardExecutor(fields));
            }
            case "closeCb" -> {
                executors.put(method, new CloseCbExecutor());
            }
        }
    }
}
