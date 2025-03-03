package com.github.lltal.filler.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WdsTelegramBotApplicaton {
    private static final Logger log = LoggerFactory.getLogger(WdsTelegramBotApplicaton.class);

    public WdsTelegramBotApplicaton() {
    }

    public static void main(String[] args) {
        log.error("not executing lib");
        SpringApplication.run(InitTelegramBotStarter.class, args);
    }
}
