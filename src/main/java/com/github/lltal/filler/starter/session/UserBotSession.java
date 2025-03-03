package com.github.lltal.filler.starter.session;

import com.github.lltal.filler.starter.command.TypeCommand;

import java.util.ArrayList;
import java.util.function.Supplier;

public class UserBotSession {
    private String nameCommand;
    private TypeCommand typeLastCommand;
    private final Long userId;
    private final ArrayList<String> lastMessage;
    private Object data;
    private long timeUpdateSession;

    public static Supplier<UserBotSession> create(Long userId, TypeCommand typeLastCommand) {
        return () -> {
            return new UserBotSession(userId, typeLastCommand);
        };
    }

    public UserBotSession(Long userId, TypeCommand typeLastCommand) {
        this("", typeLastCommand, userId);
    }

    public UserBotSession(String nameCommand, TypeCommand typeLastCommand, Long userId) {
        this.userId = userId;
        this.lastMessage = new ArrayList();
        this.clear(nameCommand, typeLastCommand);
    }

    public void clear(String nameCommand, TypeCommand typeLastCommand) {
        this.nameCommand = nameCommand;
        this.lastMessage.clear();
        this.data = null;
        this.typeLastCommand = typeLastCommand;
        this.updateTime();
    }

    public void addTextMessage(String text) {
        this.lastMessage.add(text);
        this.updateTime();
    }

    public void updateTime() {
        this.timeUpdateSession = System.currentTimeMillis();
    }

    public synchronized Object getData() {
        return this.data;
    }

    public synchronized void setData(Object data) {
        this.data = data;
    }

    public void stop() {
        this.clear("", TypeCommand.NONE);
    }

    public String getNameCommand() {
        return this.nameCommand;
    }

    public TypeCommand getTypeLastCommand() {
        return this.typeLastCommand;
    }

    public Long getUserId() {
        return this.userId;
    }

    public ArrayList<String> getLastMessage() {
        return this.lastMessage;
    }

    public long getTimeUpdateSession() {
        return this.timeUpdateSession;
    }

    public String toString() {
        String var10000 = this.getNameCommand();
        return "UserBotSession(nameCommand=" + var10000 + ", typeLastCommand=" + this.getTypeLastCommand() + ", userId=" + this.getUserId() + ", lastMessage=" + this.getLastMessage() + ", data=" + this.getData() + ", timeUpdateSession=" + this.getTimeUpdateSession() + ")";
    }
}
