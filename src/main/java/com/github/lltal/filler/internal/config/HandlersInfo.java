package com.github.lltal.filler.internal.config;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoInfo;
import com.github.lltal.filler.shared.ifc.CustomButtonHandler;
import com.github.lltal.filler.shared.ifc.CustomFilleeHandler;
import lombok.Getter;

import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HandlersInfo {
    //beanName vs bean
    @Getter
    private static final Map<String, CustomButtonHandler> buttonsHandlers = new HashMap<>();

    //beanName vs bean
    @Getter
    private static final Map<String, CustomFilleeHandler> fieldsHandlers = new HashMap<>();

    @Getter
    private static Set<DtoInfo> dtoInfos = new HashSet<>();
}
