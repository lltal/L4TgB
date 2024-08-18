package com.github.lltal.filler.shared.annotation;

import com.github.lltal.filler.shared.ifc.AbstractResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Указывает, что надо создать бин заполнителя для dto.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE
})
public @interface Fillee {
    /**
     * По дефолту бин не будет создан
     */
    String senderBeanName() default "";

    /**
     * По дефолту бин не будет создан
     */
    String fillerBeanName() default "";

    /**
     * По дефолту бин не будет создан
     */
    String resolverBeanName() default "";
}
