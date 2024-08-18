package com.github.lltal.filler.internal.invocations.filler;

import com.github.lltal.filler.internal.invocations.filler.handlers.impl.FilleeFieldHandlerImpl;
import com.github.lltal.filler.internal.invocations.common.ifc.MethodExecutor;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoInfo;
import com.github.lltal.filler.internal.invocations.filler.executors.FillerExecutorMethodFactory;
import com.github.lltal.filler.internal.invocations.sender.SenderBeanFactory;
import com.github.lltal.filler.shared.annotation.Fillee;
import com.github.lltal.filler.shared.ifc.AbstractFiller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class FillerBeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(SenderBeanFactory.class);

    public static void regFillerBean(
            ConfigurableApplicationContext applicationContext,
            DtoInfo dtoInfo,
            Fillee fillee
    ) {
        if(fillee.fillerBeanName().isEmpty()) {
            return;
        }

        dtoInfo.getFields().values()
                .forEach(fieldInfo ->
                        fieldInfo.setFilleeHandler(
                                new FilleeFieldHandlerImpl(
                                        fieldInfo.getField())));

        Map<Method, MethodExecutor> executors =
                FillerExecutorMethodFactory.getExecutors(dtoInfo.getFields());

        Object fillerBean = Proxy.newProxyInstance(
                dtoInfo.getClass().getClassLoader(),
                new Class[]{AbstractFiller.class},
                new FillerInvocationHandler(executors));

        logger.info("registered filler bean: {}, bean name: {}", fillerBean, fillee.fillerBeanName());

        applicationContext
                .getBeanFactory()
                .registerSingleton(
                        fillee.fillerBeanName(),
                        fillerBean);
    }
}
