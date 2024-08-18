package com.github.lltal.filler.internal.config.properties;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FillerConfigurationProperties {
    private static final Map<String, Object> properties = new HashMap<>();

    public static void putProperty(String key, Object value) {
        properties.put(key, value);
    }

    public static Object getProperty(String key) {
        return properties.get(key);
    }

    public static Map<String, Object> getProperties() {
        return new HashMap<>(properties);
    }
}
