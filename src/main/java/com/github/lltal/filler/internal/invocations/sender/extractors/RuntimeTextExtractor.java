package com.github.lltal.filler.internal.invocations.sender.extractors;

public class RuntimeTextExtractor {

    public static String extractRuntimeText(Object[] args) {
        Object[] params = (Object[]) args[2];
        return params.length > 0 ? (String) params[0] : "";
    }
}
