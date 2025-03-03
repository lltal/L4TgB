package com.github.lltal.filler.internal.invocations.sender.executors.impl;

import com.github.lltal.filler.internal.invocations.common.ifc.ReturnableMethodExecutor;
import com.github.lltal.filler.starter.command.CommandContext;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public class CloseCbExecutor implements ReturnableMethodExecutor<BotApiMethod> {

    @Override
    public BotApiMethod execute(Object[] args) {

        CommandContext context = (CommandContext)args[0];
        return AnswerCallbackQuery
                .builder()
                .callbackQueryId(
                        context
                                .getUpdate()
                                .getCallbackQuery()
                                .getId())
                .build();
    }
}
