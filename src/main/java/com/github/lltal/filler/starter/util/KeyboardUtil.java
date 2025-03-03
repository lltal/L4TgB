package com.github.lltal.filler.starter.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lltal.filler.starter.callback.CallbackDataSender;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public KeyboardUtil() {
    }

    public static InlineKeyboardMarkup inline(CallbackDataSender[][] rowsText) {
        List<List<InlineKeyboardButton>> rows = new ArrayList();
        CallbackDataSender[][] var2 = rowsText;
        int var3 = rowsText.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            CallbackDataSender[] rowsData = var2[var4];
            List<InlineKeyboardButton> rowKey = new ArrayList();
            CallbackDataSender[] var7 = rowsData;
            int var8 = rowsData.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                CallbackDataSender dataCell = var7[var9];
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(dataCell.getText());

                try {
                    button.setCallbackData(objectMapper.writeValueAsString(dataCell.getData()));
                } catch (JsonProcessingException var13) {
                    throw new RuntimeException(var13);
                }

                rowKey.add(button);
            }

            rows.add(rowKey);
        }

        InlineKeyboardMarkup inline = new InlineKeyboardMarkup();
        inline.setKeyboard(rows);
        return inline;
    }

    public static ReplyKeyboardMarkup replayKeyboard(String[][] texts) {
        List<KeyboardRow> rowsList = new ArrayList();
        String[][] var2 = texts;
        int var3 = texts.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String[] row = var2[var4];
            KeyboardRow rowData = new KeyboardRow();
            String[] var7 = row;
            int var8 = row.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                String cell = var7[var9];
                rowData.add(cell);
            }

            rowsList.add(rowData);
        }

        ReplyKeyboardMarkup replyKeyboard = new ReplyKeyboardMarkup();
        replyKeyboard.setKeyboard(rowsList);
        replyKeyboard.setSelective(true);
        replyKeyboard.setResizeKeyboard(true);
        return replyKeyboard;
    }

    public static ReplyKeyboardRemove removeReplayKeyboard() {
        return new ReplyKeyboardRemove(true, true);
    }
}

