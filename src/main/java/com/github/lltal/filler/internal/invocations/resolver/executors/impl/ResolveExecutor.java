package com.github.lltal.filler.internal.invocations.resolver.executors.impl;

import com.github.lltal.filler.internal.invocations.common.ifc.MethodExecutor;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.common.annotations.ButtonAnnotationHandler;
import com.github.lltal.filler.shared.ifc.Countable;
import com.github.lltal.filler.starter.command.CommandContext;

import java.util.Map;

public class ResolveExecutor implements MethodExecutor {
    private final Map<Integer, DtoFieldInfo> fields;

    public ResolveExecutor(
            Map<Integer, DtoFieldInfo> fields
    ) {
        this.fields = fields;
    }

    @Override
    public void execute(Object[] args) {

        Countable dto = (Countable) args[0];
        int currentCount  = dto.getCount();

        DtoFieldInfo fieldInfo = fields.get(currentCount);

        ButtonAnnotationHandler buttonHandler = null;
        CommandContext context = (CommandContext) args[1];

        if (context.getName() != null) {
            buttonHandler = fields.get(currentCount).getButtonsVsClickHandler().get(context.getName());
        }

        if (buttonHandler != null) {

            buttonHandler.handleCLick((Countable) args[0], context.getName(), context);

        } else if(fieldInfo.getCustomFilleeHandler() != null) {

            fieldInfo.getCustomFilleeHandler().handleField((Countable) args[0], context);

        } else {

            fieldInfo.getFilleeHandler().handleField((Countable) args[0], context.getName());
        }
    }
}
