package com.github.lltal.filler.starter.session;

import com.github.lltal.filler.starter.command.TypeCommand;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface SessionManagerService {
    ChatBotSession getSession(Long chatId);

    UserBotSession getUserSession(Long chatId, Long userId, TypeCommand typeCommand);

    UserBotSession getUserSession(Message message);

    UserBotSession getUserSession(CallbackQuery callbackQuery);
}

