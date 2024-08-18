package com.github.lltal.filler.internal.checker;

import com.github.lltal.filler.shared.annotation.Fillee;

public class AnnotationChecker {

    public static void checkFilleeAnnotation(Class<?> dtoClass) {
        if (!dtoClass.isAnnotationPresent(Fillee.class)) {
            throw new IllegalStateException("dto must be annotated with Fillee");
        }
    }

}
