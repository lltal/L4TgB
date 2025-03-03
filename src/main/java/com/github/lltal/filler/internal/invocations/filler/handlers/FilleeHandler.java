package com.github.lltal.filler.internal.invocations.filler.handlers;

import com.github.lltal.filler.internal.invocations.common.ifc.Handler;
import com.github.lltal.filler.shared.ifc.Countable;

public interface FilleeHandler<T extends Countable, V> extends Handler<T> {

    void handleField(T dto, V value);
}
