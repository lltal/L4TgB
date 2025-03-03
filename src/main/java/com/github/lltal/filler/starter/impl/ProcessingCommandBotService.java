package com.github.lltal.filler.starter.impl;

import com.github.lltal.filler.starter.TelegramLongPollingEngine;
import com.github.lltal.filler.starter.command.ManagerCommandAndSessionGroupService;
import com.github.lltal.filler.starter.ifs.HandlerBotUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ProcessingCommandBotService implements HandlerBotUpdate {
    private static final Logger log = LoggerFactory.getLogger(ProcessingCommandBotService.class);
    private final ManagerCommandAndSessionGroupService managerCommandAndSessionGroupService;

    public void update(TelegramLongPollingEngine engine, Update update) {
        this.managerCommandAndSessionGroupService.processingCommand(engine, update);
    }

    public int priority() {
        return 100;
    }

    public ProcessingCommandBotService(final ManagerCommandAndSessionGroupService managerCommandAndSessionGroupService) {
        this.managerCommandAndSessionGroupService = managerCommandAndSessionGroupService;
    }
}

