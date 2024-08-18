package com.github.lltal.filler.internal.invocations.sender.messages.impl;

import com.github.lltal.filler.internal.invocations.sender.messages.MessageCreator;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

public class MessageWithKeyboardCreator implements MessageCreator {
    private final String text;
    private final ReplyKeyboard keyboard;

    public MessageWithKeyboardCreator(
            String text,
            ReplyKeyboard keyboard
    ) {
        this.text = text;
        this.keyboard = keyboard;
    }

    @Override
    public BotApiMethod create(Long chatId, String runtimeText) {

        if (text.isEmpty() && runtimeText.isEmpty()) {
            return null;
        }

        return SendMessage
                .builder()
                .text(runtimeText.isEmpty() ? text : runtimeText)
                .replyMarkup(keyboard)
                .chatId(chatId)
                .build();
    }
}
