package com.github.lltal.filler.internal.invocations.sender.messages;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

public interface MessageCreator {
    BotApiMethod create(Long chatId, String runtimeText);
}
