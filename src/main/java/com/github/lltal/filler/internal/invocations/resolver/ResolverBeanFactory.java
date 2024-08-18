package com.github.lltal.filler.internal.invocations.resolver;

import com.github.lltal.filler.internal.config.HandlersInfo;
import com.github.lltal.filler.internal.invocations.common.ifc.MethodExecutor;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoInfo;
import com.github.lltal.filler.internal.invocations.filler.FillerInvocationHandler;
import com.github.lltal.filler.internal.invocations.resolver.executors.ResolverMethodExecutorFactory;
import com.github.lltal.filler.internal.invocations.resolver.handlers.FieldAnnotationHandlerRunner;
import com.github.lltal.filler.internal.invocations.sender.SenderBeanFactory;
import com.github.lltal.filler.shared.annotation.Fillee;
import com.github.lltal.filler.shared.ifc.AbstractFiller;
import com.github.lltal.filler.shared.ifc.AbstractResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class ResolverBeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(SenderBeanFactory.class);

    public static void regResolverBean(
            ConfigurableApplicationContext applicationContext,
            DtoInfo dtoInfo,
            Fillee fillee
    ) {
        if(fillee.resolverBeanName().isEmpty()) {
            return;
        }

        FieldAnnotationHandlerRunner.run(dtoInfo);

        Map<Method, MethodExecutor> executors =
                ResolverMethodExecutorFactory
                        .getExecutors(dtoInfo.getFields());

        Object resolverBean = Proxy.newProxyInstance(
                dtoInfo.getClass().getClassLoader(),
                new Class[]{AbstractResolver.class},
                new ResolverInvocationHandler(executors));

        logger.info("registered resolver bean: {}, bean name: {}", resolverBean, fillee.resolverBeanName());

        applicationContext
                .getBeanFactory()
                .registerSingleton(
                        fillee.resolverBeanName(),
                        resolverBean);
    }
}
