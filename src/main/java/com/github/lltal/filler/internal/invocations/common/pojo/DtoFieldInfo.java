package com.github.lltal.filler.internal.invocations.common.pojo;

import com.github.lltal.filler.internal.invocations.common.annotations.ButtonAnnotationHandler;
import com.github.lltal.filler.internal.invocations.filler.handlers.FilleeHandler;
import com.github.lltal.filler.internal.invocations.sender.messages.MessageCreator;
import com.github.lltal.filler.shared.ifc.CustomFilleeHandler;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class DtoFieldInfo {
    @NonNull
    private Field field;
    
    private FilleeHandler filleeHandler;
    
    private CustomFilleeHandler customFilleeHandler;
    
    private String filleeHandlerBeanName;

    // колбек кнопки против имени бина, который должен его обработать
    private final Map<String, String> buttonVsBeanName = new HashMap<>();

    // колбек кнопки против бина, который должен его обработать
    private final Map<String, ButtonAnnotationHandler> buttonsVsClickHandler = new HashMap<>();

    private MessageCreator messageCreatorWithoutKeyboard;

    private MessageCreator messageCreatorWithKeyboard;
}
