package com.github.lltal.filler.shared.ifc;

import com.github.lltal.filler.shared.annotation.FilleeField;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.wdeath.telegram.bot.starter.command.CommandContext;

import java.util.List;

public interface AbstractSender {
    /**
     * Получить сообщение, для текущего поля.
     * Текущее поле - поле, на которое указывает счетчик внутри dto.
     */
    BotApiMethod getNextMessage(Countable object, Long chatId, Object... params);

    /**
     * Получить сообщение, для текущего поля.
     * Текущее поле - поле, на которое указывает счетчик внутри dto.
     */
    BotApiMethod getNextMessageWithoutKeyboard(Countable object, Long chatId, Object... params);

    /**
     * Закрыть колбек
     * <p> При вызове не из колбека поведение программы окажется не предсказуемым.
     */
    BotApiMethod closeCb(CommandContext context);
}
