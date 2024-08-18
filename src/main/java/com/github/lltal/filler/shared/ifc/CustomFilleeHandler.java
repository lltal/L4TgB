package com.github.lltal.filler.shared.ifc;

import com.github.lltal.filler.internal.invocations.common.ifc.Handler;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

public interface CustomFilleeHandler<T extends Countable> extends Handler<T> {
    void handleField(T dto, CommandContext context);
}
