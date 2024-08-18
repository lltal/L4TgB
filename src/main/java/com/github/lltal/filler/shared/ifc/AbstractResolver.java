package com.github.lltal.filler.shared.ifc;

import com.github.lltal.filler.shared.annotation.Button;
import com.github.lltal.filler.shared.annotation.Fillee;
import com.github.lltal.filler.shared.annotation.FilleeField;
import com.github.lltal.filler.shared.annotation.Keyboard;
import org.springframework.lang.Nullable;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

import java.util.List;

/**
 * Интерфейс, описывающий методы заполнителя
 * @param <T> Объект, обычно DTO, которое надо заполнить.
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
