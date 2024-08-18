package com.github.lltal.filler.internal.invocations.filler.executors.impl;

import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.common.ifc.MethodExecutor;
import com.github.lltal.filler.shared.ifc.Countable;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

import java.util.Map;


public class FillExecutor implements MethodExecutor {

    private final Map<Integer, DtoFieldInfo> fields;

    public FillExecutor(
            Map<Integer, DtoFieldInfo> fields
    ) {
        this.fields = fields;
    }

    @Override
    public void execute(Object[] args) {

        Countable dto = (Countable) args[0];
        int currentCount  = dto.getCount();

        DtoFieldInfo fieldInfo = fields.get(currentCount);

        fieldInfo.getFilleeHandler().handleField((Countable) args[0], args[1]);

        dto.setCount(currentCount + 1);
    }
}

