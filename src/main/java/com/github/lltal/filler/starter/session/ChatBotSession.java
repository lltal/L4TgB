package com.github.lltal.filler.starter.session;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ChatBotSession {
    private final Long chatId;
    private final ConcurrentHashMap<Long, UserBotSession> usersMap;
    private long timeUpdateSession;

    public ChatBotSession(Long chatId) {
        this.chatId = chatId;
        this.usersMap = new ConcurrentHashMap();
        this.updateTime();
    }

    public UserBotSession getUser(Long userId, Supplier<UserBotSession> supplier) {
        UserBotSession userBotSession = (UserBotSession)this.usersMap.get(userId);
        if (userBotSession == null) {
            userBotSession = (UserBotSession)supplier.get();
            this.usersMap.put(userId, userBotSession);
        }

        userBotSession.updateTime();
        return userBotSession;
    }

    public void updateTime() {
        this.timeUpdateSession = System.currentTimeMillis();
    }

    public Long getChatId() {
        return this.chatId;
    }

    public ConcurrentHashMap<Long, UserBotSession> getUsersMap() {
        return this.usersMap;
    }

    public long getTimeUpdateSession() {
        return this.timeUpdateSession;
    }

    public String toString() {
        Long var10000 = this.getChatId();
        return "ChatBotSession(chatId=" + var10000 + ", usersMap=" + this.getUsersMap() + ", timeUpdateSession=" + this.getTimeUpdateSession() + ")";
    }
}

