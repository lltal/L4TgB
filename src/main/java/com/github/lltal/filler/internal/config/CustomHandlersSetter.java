package com.github.lltal.filler.internal.config;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoInfo;
import com.github.lltal.filler.internal.invocations.sender.SenderBeanFactory;
import com.github.lltal.filler.shared.ifc.CustomButtonHandler;
import com.github.lltal.filler.shared.ifc.CustomFilleeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CustomHandlersSetter {

    public static void setCustomHandlersToInvocationHandler() {
        Set<DtoInfo> dtoInfos = HandlersInfo.getDtoInfos();

        for (DtoInfo dtoInfo : dtoInfos) {
            
            for (DtoFieldInfo fieldInfo : dtoInfo.getFields().values()) {

                Map<String, String> buttonVsBeanName = fieldInfo.getButtonVsBeanName();
                Map<String, CustomButtonHandler> buttonsHandlers = HandlersInfo.getButtonsHandlers();

                for (Map.Entry<String, String> pair : buttonVsBeanName.entrySet()) {

                    CustomButtonHandler customButtonHandler = buttonsHandlers.get(pair.getValue());

                    if (customButtonHandler != null) {

                        fieldInfo.getButtonsVsClickHandler().put(pair.getKey(), customButtonHandler);
                    }

                }

                String filleHandlerBeanName = fieldInfo.getFilleeHandlerBeanName();
                Map<String, CustomFilleeHandler> fieldsHandlers = HandlersInfo.getFieldsHandlers();

                if (filleHandlerBeanName != null) {

                    CustomFilleeHandler customFilleeHandler = fieldsHandlers.get(filleHandlerBeanName);

                    if (customFilleeHandler != null) {

                        fieldInfo.setCustomFilleeHandler(customFilleeHandler);
                    }
                }

            }

        }

    }

}
