package com.github.lltal.filler.internal.invocations.filler.handlers.impl;

import com.github.lltal.filler.internal.invocations.filler.handlers.FilleeHandler;
import com.github.lltal.filler.shared.ifc.Countable;
import lombok.SneakyThrows;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

import java.lang.reflect.Field;

public class FilleeFieldHandlerImpl implements FilleeHandler {
    private final Field field;

    public FilleeFieldHandlerImpl(
            Field field
    ) {
        this.field = field;
    }

    @Override
    @SneakyThrows
    public void handleField(Countable dto, Object value) {

        field.setAccessible(true);
        field.set(dto, value);
    }
}
