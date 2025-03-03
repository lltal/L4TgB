package com.github.lltal.filler.shared.ifc;

import com.github.lltal.filler.shared.annotation.Button;
import com.github.lltal.filler.shared.annotation.FilleeField;
import com.github.lltal.filler.starter.command.CommandContext;
import org.springframework.lang.Nullable;

/**
 * Интерфейс, описывающий методы заполнителя
 */
public interface AbstractResolver {
    /**
     * Вызывается, когда надо заполнить поле объекта.
     * <p> Если существует пользовательский колбек кнопки, принадлежащей полю, прописанный в {@link Button}, будет вызван колбек кнопки.
     * <p> Иначе, если существует пользовательский колбек для поля, прописанный в {@link FilleeField}, будет вызван колбек для поля.
     * <p> Иначе будет выполнено присвоение данных в поле.
     *
     * @param <V> тип поля, для которого передано значение.
     */
    @Nullable
    <V> void resolve(Countable object, CommandContext context);
}
