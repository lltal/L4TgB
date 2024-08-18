package com.github.lltal.filler.shared.config;


import com.github.lltal.filler.internal.checker.AnnotationChecker;
import com.github.lltal.filler.internal.config.HandlersInfo;
import com.github.lltal.filler.internal.config.properties.FillerConfigurationProperties;
import com.github.lltal.filler.internal.constants.FillerConstants;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoInfo;
import com.github.lltal.filler.internal.invocations.filler.FillerBeanFactory;
import com.github.lltal.filler.internal.invocations.resolver.ResolverBeanFactory;
import com.github.lltal.filler.internal.invocations.sender.SenderBeanFactory;
import com.github.lltal.filler.internal.sorter.FieldsInfoCreator;
import com.github.lltal.filler.shared.annotation.Fillee;
import com.github.lltal.filler.shared.ifc.Countable;
import lombok.NonNull;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;


public class FillerContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final Logger logger = LoggerFactory.getLogger(FillerContextInitializer.class);
    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {

        Map<String, Object> properties = FillerConfigurationProperties.getProperties();
        logger.info("got properties: {}", properties);
        properties.entrySet()
                .stream()
                .filter(pair -> pair.getKey().contains(FillerConstants.PACKAGES_TO_SCAN_PROPERTY))
                .forEach(pair -> createBeansForPackage(applicationContext, (String) pair.getValue()));
    }

    private void createBeansForPackage(
            @NonNull ConfigurableApplicationContext applicationContext,
            String packageToScan
    ) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage(packageToScan));
        Set<Class<? extends Countable>> dtoClasses = reflections.getSubTypesOf(Countable.class);

        logger.info("find classes: {}", dtoClasses);

        dtoClasses.forEach(dtoClass -> {

            AnnotationChecker.checkFilleeAnnotation(dtoClass);

            DtoInfo dtoInfo = new DtoInfo(dtoClass);
            Fillee fillee = dtoClass.getAnnotation(Fillee.class);

            FieldsInfoCreator.createFieldsInfo(dtoInfo);

            SenderBeanFactory.regSenderBean(applicationContext, dtoInfo, fillee);
            FillerBeanFactory.regFillerBean(applicationContext, dtoInfo, fillee);
            ResolverBeanFactory.regResolverBean(applicationContext, dtoInfo, fillee);

            HandlersInfo.getDtoInfos().add(dtoInfo);
        });
    }
}

