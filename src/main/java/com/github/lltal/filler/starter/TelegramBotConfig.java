package com.github.lltal.filler.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@Component
@ConditionalOnProperty({"telegram.bot.config-file"})
public class TelegramBotConfig {
    public static final Yaml YAML = new Yaml();
    private final String filePath;
    private Map<String, Object> valueMap;

    public TelegramBotConfig(TelegramBotProperty botProperty) {
        this.filePath = botProperty.getConfigFile();
        this.load();
    }

    public Object get(String key) {
        return this.valueMap == null ? null : this.valueMap.get(key);
    }

    private void load() {
        File file = new File(this.filePath);
        if (!file.exists()) {
            try {
                File parentFile = file.getParentFile();
                boolean isMkdirs = false;
                if (parentFile != null && !parentFile.exists()) {
                    isMkdirs = parentFile.mkdirs();
                }

                boolean newFileCreate = file.createNewFile();
                System.out.println("Create Bot config " + this.filePath + " - " + isMkdirs + " / " + newFileCreate);
            } catch (IOException var5) {
                throw new RuntimeException(var5);
            }
        }

        this.reload();
    }

    public void reload() {
        try {
            this.valueMap = (Map)YAML.load(new FileInputStream(this.filePath));
        } catch (FileNotFoundException var2) {
            throw new RuntimeException(var2);
        }
    }

    public String toString() {
        String var10000 = this.getFilePath();
        return "TelegramBotConfig(filePath=" + var10000 + ", valueMap=" + this.valueMap + ")";
    }

    public String getFilePath() {
        return this.filePath;
    }
}
