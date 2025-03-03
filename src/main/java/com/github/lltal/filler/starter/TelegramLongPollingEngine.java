package com.github.lltal.filler.starter;

import com.github.lltal.filler.starter.ifs.HandlerBotUpdate;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;

@Component
public class TelegramLongPollingEngine extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(TelegramLongPollingEngine.class);
    private BotSession botSession;
    private final TelegramBotProperty telegramBotProperty;
    private final List<HandlerBotUpdate> handlerBotUpdates;

    public TelegramLongPollingEngine(List<HandlerBotUpdate> listUpdates, TelegramBotProperty telegramBotProperty) {
        super(telegramBotProperty.getToken());
        this.telegramBotProperty = telegramBotProperty;
        this.handlerBotUpdates = listUpdates;
        this.handlerBotUpdates.sort(Comparator.comparingInt(HandlerBotUpdate::priority));
        List<String> nameHandlersCollect = (List)this.handlerBotUpdates.stream().map((handlerBotUpdate) -> {
            return handlerBotUpdate.getClass().getName();
        }).collect(Collectors.toList());
        log.info("Load handlers update bot: " + nameHandlersCollect);
    }

    public String getBotUsername() {
        return this.telegramBotProperty.getUsername();
    }

    public void onUpdateReceived(Update update) {
        log.info("Handler update for bot: " + update);
        this.handlerBotUpdates.forEach((handlerBotUpdate) -> {
            handlerBotUpdate.update(this, update);
        });
    }

    public void executeNotException(BotApiMethod<?> method) {
        try {
            this.execute(method);
        } catch (TelegramApiException var3) {
            log.error("Error execute in executeNotException", var3);
            throw new RuntimeException(var3);
        }
    }

    public BotSession getBotSession() {
        return this.botSession;
    }

    public void setBotSession(final BotSession botSession) {
        this.botSession = botSession;
    }
}
