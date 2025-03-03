package com.github.lltal.filler.internal.invocations.sender.messages.impl;

import com.github.lltal.filler.internal.invocations.sender.messages.MessageCreator;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MessageWithoutKeyboardCreator implements MessageCreator {

    private final String text;

    public MessageWithoutKeyboardCreator(String text) {
        this.text = text;
    }

    @Override
    public BotApiMethod create(Long chatId, String runtimeText) {

        if (text.isEmpty() && runtimeText.isEmpty()) {
            return null;
        }

        return SendMessage
                .builder()
                .text(runtimeText.isEmpty() ? text : runtimeText)
                .chatId(chatId)
                .build();
    }
}
