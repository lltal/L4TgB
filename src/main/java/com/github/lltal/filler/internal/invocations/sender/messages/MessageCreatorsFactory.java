package com.github.lltal.filler.internal.invocations.sender.messages;



import com.github.lltal.filler.internal.invocations.common.pojo.DtoFieldInfo;
import com.github.lltal.filler.internal.invocations.sender.messages.impl.MessageWithKeyboardCreator;
import com.github.lltal.filler.internal.invocations.sender.messages.impl.MessageWithoutKeyboardCreator;
import com.github.lltal.filler.shared.annotation.Button;
import com.github.lltal.filler.shared.annotation.FilleeField;
import com.github.lltal.filler.shared.annotation.Keyboard;
import com.github.lltal.filler.starter.callback.CallbackData;
import com.github.lltal.filler.starter.callback.CallbackDataSender;
import com.github.lltal.filler.starter.util.KeyboardUtil;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

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

        CallbackDataSender[][] buttons =
                Arrays.stream(keyboard.buttons())
                        .map(button -> new CallbackDataSender(
                                button.userView(),
                                new CallbackData(
                                        button.cbValue(), "")))
                        .map(sender -> new CallbackDataSender[]{sender})
                        .toArray((value -> new CallbackDataSender[value][1]));

        fieldInfo.setMessageCreatorWithKeyboard(
                new MessageWithKeyboardCreator(
                        felleeField.text(),
                        KeyboardUtil.inline(buttons)));
    }

}
