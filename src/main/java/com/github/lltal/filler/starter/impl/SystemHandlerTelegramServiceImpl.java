package com.github.lltal.filler.starter.impl;

import com.github.lltal.filler.starter.TelegramLongPollingEngine;
import com.github.lltal.filler.starter.ifs.HandlerBotUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class SystemHandlerTelegramServiceImpl implements HandlerBotUpdate {
    private static final Logger log = LoggerFactory.getLogger(SystemHandlerTelegramServiceImpl.class);

    public SystemHandlerTelegramServiceImpl() {
    }

    public void update(TelegramLongPollingEngine engine, Update update) {
        log.info("Handler update for bot: " + engine.getBotUsername());
    }

    public int priority() {
        return 30;
    }
}
