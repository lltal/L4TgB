package com.github.lltal.filler.starter;

import com.github.lltal.filler.starter.event.TelegramBotEventStart;
import com.github.lltal.filler.starter.event.TelegramBotEventStop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Service
@ConditionalOnProperty({"telegram.bot.token"})
public class TelegramBotApiStarterService {
    private static final Logger log = LoggerFactory.getLogger(TelegramBotApiStarterService.class);
    private final TelegramLongPollingEngine longPollingEngine;
    private final TelegramBotsApi telegramBotsApi;

    public TelegramBotApiStarterService(TelegramLongPollingEngine longPollingEngine) {
        this.longPollingEngine = longPollingEngine;

        try {
            this.telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException var3) {
            throw new RuntimeException(var3);
        }
    }

    @PostConstruct
    public void postConstructor() {
        this.startBot();
    }

    @EventListener({TelegramBotEventStart.class})
    public void startBot() {
        log.info("Start bot: @" + this.longPollingEngine.getBotUsername());
        this.startAndRegistration(this.longPollingEngine);
    }

    @EventListener({TelegramBotEventStop.class})
    public void stopBot() {
        log.info("Stop bot @" + this.longPollingEngine.getBotUsername());
        this.stopBot(this.longPollingEngine);
    }

    private void startAndRegistration(TelegramLongPollingEngine engine) {
        try {
            BotSession botSession = this.telegramBotsApi.registerBot(engine);
            engine.setBotSession(botSession);
        } catch (TelegramApiException var3) {
            log.error("Registration bot in telegram fail", var3);
        }

    }

    private void stopBot(TelegramLongPollingEngine engine) {
        engine.getBotSession().stop();
    }
}

