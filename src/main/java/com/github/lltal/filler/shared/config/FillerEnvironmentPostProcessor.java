package com.github.lltal.filler.shared.config;

import com.github.lltal.filler.internal.config.properties.FillerConfigurationProperties;
import com.github.lltal.filler.internal.constants.FillerConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class FillerEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment,
            SpringApplication application
    ) {
        Object packageToScan;
        int index = 0;

        while ((packageToScan = environment.getProperty(createMapKey(index++))) != null) {

            FillerConfigurationProperties.putProperty(
                    FillerConstants.PACKAGES_TO_SCAN_PROPERTY, packageToScan);
        }
    }

    private String createMapKey(int index) {
        return String.format(
                "%s[%d]",
                FillerConstants.PACKAGES_TO_SCAN_PROPERTY,
                index);
    }
}
