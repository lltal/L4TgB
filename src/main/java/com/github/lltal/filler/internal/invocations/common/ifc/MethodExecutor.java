package com.github.lltal.filler.internal.invocations.common.ifc;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


public interface MethodExecutor {
    void execute(Object[] args);
}
