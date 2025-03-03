package com.github.lltal.filler.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.github.lltal.filler.starter"})
public class InitTelegramBotStarter {
    private static final Logger log = LoggerFactory.getLogger(InitTelegramBotStarter.class);

    public InitTelegramBotStarter() {
    }
}
