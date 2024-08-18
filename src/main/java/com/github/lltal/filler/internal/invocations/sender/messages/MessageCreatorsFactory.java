package com.github.lltal.filler.internal.invocations.sender.messages;



import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.sender.messages.impl.MessageWithKeyboardCreator;
import com.github.lltal.filler.internal.invocations.sender.messages.impl.MessageWithoutKeyboardCreator;
import com.github.lltal.filler.shared.annotation.FilleeField;
import com.github.lltal.filler.shared.annotation.Keyboard;
import ru.wdeath.telegram.bot.starter.callback.CallbackData;
import ru.wdeath.telegram.bot.starter.callback.CallbackDataSender;
import ru.wdeath.telegram.bot.starter.util.KeyboardUtil;

import java.lang.reflect.Field;
import java.util.Arrays;

public class MessageCreatorsFactory {

    public static void createForField(DtoFieldInfo fieldInfo) {
        Field field = fieldInfo.getField();

        FilleeField felleeField = field.getDeclaredAnnotation(FilleeField.class);


        fieldInfo.setMessageCreatorWithoutKeyboard(
                new MessageWithoutKeyboardCreator(felleeField.text()));

        if (!field.isAnnotationPresent(Keyboard.class)) {
            return;
        }

        Keyboard keyboard = field.getDeclaredAnnotation(Keyboard.class);

        CallbackDataSender[][] buttons = {
                Arrays.stream(keyboard.buttons())
                        .map(button -> new CallbackDataSender(
                                button.userView(),
                                new CallbackData(
                                        button.cbValue(), "")))
                        .toArray(CallbackDataSender[]::new)};

        fieldInfo.setMessageCreatorWithKeyboard(
                new MessageWithKeyboardCreator(
                        felleeField.text(),
                        KeyboardUtil.inline(buttons)));
    }

}
