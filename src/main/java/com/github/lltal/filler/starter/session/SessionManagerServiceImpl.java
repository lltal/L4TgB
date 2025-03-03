package com.github.lltal.filler.starter.session;

import com.github.lltal.filler.starter.TelegramBotProperty;
import com.github.lltal.filler.starter.command.TypeCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@ConditionalOnProperty({"telegram.bot.session.enable"})
public class SessionManagerServiceImpl implements SessionManagerService {
    private static final Logger log = LoggerFactory.getLogger(SessionManagerServiceImpl.class);
    private final ConcurrentHashMap<Long, ChatBotSession> chatBotMap;
    private final ScheduledExecutorService executorService;
    private final TelegramBotProperty botProperty;

    public SessionManagerServiceImpl(TelegramBotProperty botProperty) {
        this.botProperty = botProperty;
        this.chatBotMap = new ConcurrentHashMap();
        this.executorService = Executors.newSingleThreadScheduledExecutor((r) -> {
            Thread thread = new Thread(r, "ClearSessionBots");
            thread.setDaemon(true);
            thread.setPriority(1);
            return thread;
        });
    }

    @PostConstruct
    public void init() {
        log.info("Starting session chat and user...");
        int timeLifeChat = this.botProperty.getSession().getTimeLife().getChat();
        int timeLifeUser = this.botProperty.getSession().getTimeLife().getUser();
        this.executorService.scheduleAtFixedRate(() -> {
            log.info("Clear sessionChat to timeout");
            this.clearSessionChatTimeout();
        }, (long)(timeLifeChat / 2), (long)(timeLifeChat / 4), TimeUnit.MINUTES);
        this.executorService.scheduleAtFixedRate(() -> {
            log.info("Clear sessionUser to timeout");
            this.clearSessionUserTimeout();
        }, (long)(timeLifeUser / 2), (long)(timeLifeUser / 4), TimeUnit.MINUTES);
        log.info("Start manager session: chat=" + timeLifeChat + "s, user=" + timeLifeUser + "s");
    }

    private void clearSessionChatTimeout() {
        synchronized(this.chatBotMap) {
            Set<Map.Entry<Long, ChatBotSession>> entries = this.chatBotMap.entrySet();
            Iterator var3 = entries.iterator();

            while(var3.hasNext()) {
                Map.Entry<Long, ChatBotSession> entry = (Map.Entry)var3.next();
                if (!this.isLifeSession(((ChatBotSession)entry.getValue()).getTimeUpdateSession(), (long)this.botProperty.getSession().getTimeLife().getChat())) {
                    this.chatBotMap.remove(entry.getKey());
                }
            }

        }
    }

    private void clearSessionUserTimeout() {
        synchronized(this.chatBotMap) {
            Set<Map.Entry<Long, ChatBotSession>> entries = this.chatBotMap.entrySet();
            Iterator var3 = entries.iterator();

            while(var3.hasNext()) {
                Map.Entry<Long, ChatBotSession> entry = (Map.Entry)var3.next();
                Set<Map.Entry<Long, UserBotSession>> entryUsers = ((ChatBotSession)entry.getValue()).getUsersMap().entrySet();
                Iterator var6 = entryUsers.iterator();

                while(var6.hasNext()) {
                    Map.Entry<Long, UserBotSession> entryUser = (Map.Entry)var6.next();
                    if (!this.isLifeSession(((UserBotSession)entryUser.getValue()).getTimeUpdateSession(), (long)this.botProperty.getSession().getTimeLife().getUser())) {
                        ((ChatBotSession)entry.getValue()).getUsersMap().remove(entryUser.getKey());
                    }
                }
            }

        }
    }

    private boolean isLifeSession(long create, long life) {
        long current = System.currentTimeMillis();
        return create + life > current;
    }

    public ChatBotSession getSession(Long chatId) {
        return this.getAndCreate(chatId);
    }

    public UserBotSession getUserSession(Message message) {
        return this.getUserSession(message.getChatId(), message.getFrom().getId(), TypeCommand.MESSAGE);
    }

    public UserBotSession getUserSession(CallbackQuery callbackQuery) {
        return this.getUserSession(callbackQuery.getMessage().getChatId(), callbackQuery.getFrom().getId(), TypeCommand.CALLBACK);
    }

    public UserBotSession getUserSession(Long chatId, Long userId, TypeCommand typeCommand) {
        ChatBotSession chatBotSession = this.getAndCreate(chatId);
        return chatBotSession.getUser(userId, UserBotSession.create(chatId, typeCommand));
    }

    private ChatBotSession getAndCreate(Long chatId) {
        synchronized(this.chatBotMap) {
            ChatBotSession chatBotSession = (ChatBotSession)this.chatBotMap.get(chatId);
            if (chatBotSession == null) {
                chatBotSession = new ChatBotSession(chatId);
                this.chatBotMap.put(chatId, chatBotSession);
            }

            chatBotSession.updateTime();
            return chatBotSession;
        }
    }
}

