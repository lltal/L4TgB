package com.github.lltal.filler.internal.invocations.sender;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoInfo;
import com.github.lltal.filler.internal.invocations.common.ifc.ReturnableMethodExecutor;
import com.github.lltal.filler.internal.invocations.sender.executors.SenderMethodExecutorFactory;
import com.github.lltal.filler.internal.invocations.sender.messages.MessageCreatorsFactory;
import com.github.lltal.filler.shared.annotation.Fillee;
import com.github.lltal.filler.shared.config.FillerContextInitializer;
import com.github.lltal.filler.shared.ifc.AbstractSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class SenderBeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(SenderBeanFactory.class);
    public static void regSenderBean(
            ConfigurableApplicationContext applicationContext,
            DtoInfo dtoInfo,
            Fillee fillee
    ) {
        if(fillee.senderBeanName().isEmpty()) {
            return;
        }

        dtoInfo.getFields()
                .values()
                .forEach(MessageCreatorsFactory::createForField);

        Map<Method, ReturnableMethodExecutor> executors =
                SenderMethodExecutorFactory.getExecutors(dtoInfo.getFields());

        Object senderBean = Proxy.newProxyInstance(
                dtoInfo.getClass().getClassLoader(),
                new Class[]{AbstractSender.class},
                new SenderInvocationHandler(executors));

        logger.info("registered sender bean: {}, bean name: {}", senderBean, fillee.senderBeanName());

        applicationContext
                .getBeanFactory()
                .registerSingleton(
                        fillee.senderBeanName(),
                        senderBean);
    }
}
