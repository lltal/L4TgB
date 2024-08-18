package com.github.lltal.filler.internal.invocations.common.annotations;

import com.github.lltal.filler.internal.invocations.common.ifc.Handler;
import com.github.lltal.filler.shared.ifc.Countable;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

public interface ButtonAnnotationHandler<T extends Countable> extends Handler<T> {

    void handleCLick(T dto, String cbData, CommandContext context);
}
