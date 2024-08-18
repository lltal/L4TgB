package com.github.lltal.filler.shared.annotation;

import com.github.lltal.filler.shared.ifc.AbstractSender;
import com.github.lltal.filler.shared.ifc.Countable;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD
})
public @interface FilleeField {
    /**
     * Текст сообщений, которое будет создано при заполнении текущего поля.
     * <p>Если стоит дефолтное значение, то ожидается, что значение текста будет передано при вызове {@link AbstractSender#getNextMessage(Countable, CommandContext, Long, Object...)}
     * <p>Если значение не передано, то вернется null.
     */
    String text() default "";

    /**
     * Порядок, в котором заполняются поля.
     * По дефолту поля заполняются сверху вниз.
     * При изменении значения order, поля сортируются по убыванию значения.
     */
    int order() default  -1;

    /**
     * Кастомный обработчик ответа пользователя,
     * который придет для заполнения текущего поля
     */
    String customFillHandler() default "";
}
