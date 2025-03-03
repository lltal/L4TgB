package com.github.lltal.filler.starter.ifs;

import com.github.lltal.filler.starter.TelegramLongPollingEngine;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface HandlerBotUpdate {
    void update(TelegramLongPollingEngine engine, Update update);

    int priority();
}
