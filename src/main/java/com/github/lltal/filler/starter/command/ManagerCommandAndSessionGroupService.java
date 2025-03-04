package com.github.lltal.filler.starter.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lltal.filler.starter.TelegramLongPollingEngine;
import com.github.lltal.filler.starter.callback.CallbackData;
import com.github.lltal.filler.starter.session.ChatBotSession;
import com.github.lltal.filler.starter.session.SessionManagerService;
import com.github.lltal.filler.starter.session.UserBotSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ManagerCommandAndSessionGroupService {
    private static final Logger log = LoggerFactory.getLogger(ManagerCommandAndSessionGroupService.class);
    private final SessionManagerService sessionManagerService;
    private final ManagerCommandContainerService commandContainerService;
    private final ObjectMapper jsonMapper;

    public ManagerCommandAndSessionGroupService(final SessionManagerService sessionManagerService, final ManagerCommandContainerService commandContainerService, final ObjectMapper jsonMapper) {
        this.sessionManagerService = sessionManagerService;
        this.commandContainerService = commandContainerService;
        this.jsonMapper = jsonMapper;
    }

    public void processingCommand(TelegramLongPollingEngine engine, Update update) {
        CommandContext contextCommand = this.createContextCommand(engine, update);
        if (contextCommand != null) {
            CommandContainer commandContainer = this.commandContainerService.get(contextCommand.getName(), contextCommand.getTypeCommand());
            if (contextCommand.getUserBotSession() != null) {
                this.processingOnSession(commandContainer, contextCommand);
            } else {
                this.processingNotOnSession(commandContainer, contextCommand);
            }

        }
    }

    private CommandContext createContextCommand(TelegramLongPollingEngine engine, Update update) {
        CommandContext commandContext = null;
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("Handle callback for processing! Text: " + callbackQuery.getData());
            commandContext = this.createContext(callbackQuery);
        } else if (update.hasMessage()) {
            Message message = update.getMessage();
            log.info("Find text command for message: " + message.getText());
            commandContext = this.createContext(message);
        } else {
            log.warn("Not find command for message");
        }

        if (commandContext != null) {
            commandContext.setEngine(engine);
            commandContext.setUpdate(update);
        }

        return commandContext;
    }

    private CommandContext createContext(Message message) {
        String textMessage = message.getText();
        if (textMessage == null) {
            textMessage = message.getCaption();
        }

        String[] words = new String[0];
        String command = "";
        if (textMessage != null) {
            words = textMessage.split(" ");
            if (words.length == 0) {
                return null;
            }

            command = words[0].split("@")[0];
        }

        CommandContext commandContext = new CommandContext();
        commandContext.setTypeCommand(TypeCommand.MESSAGE);
        commandContext.setName(command);
        commandContext.setData(words);
        commandContext.setUserBotSession(this.getUserBotSession(message));
        commandContext.setChatBotSession(this.getChatBotSession(message));
        return commandContext;
    }

    private CommandContext createContext(CallbackQuery callbackQuery) {
        if (callbackQuery.getData() == null) {
            return null;
        } else {
            CallbackData callbackData;
            try {
                callbackData = (CallbackData)this.jsonMapper.readValue(callbackQuery.getData(), CallbackData.class);
            } catch (JsonProcessingException var4) {
                log.error("Processing \"{}\" json callback to object ERROR - {}", callbackQuery.getData(), var4.getMessage());
                return null;
            }

            CommandContext commandContext = new CommandContext();
            commandContext.setTypeCommand(TypeCommand.CALLBACK);
            commandContext.setName(callbackData.getCommand());
            commandContext.setData(callbackData.getData());
            commandContext.setUserBotSession(this.getUserBotSession(callbackQuery));
            commandContext.setChatBotSession(this.getChatBotSession(callbackQuery));
            return commandContext;
        }
    }

    private UserBotSession getUserBotSession(CallbackQuery callbackQuery) {
        return this.sessionManagerService == null ? null : this.sessionManagerService.getUserSession(callbackQuery);
    }

    private UserBotSession getUserBotSession(Message message) {
        return this.sessionManagerService == null ? null : this.sessionManagerService.getUserSession(message);
    }

    private ChatBotSession getChatBotSession(CallbackQuery callbackQuery) {
        return this.sessionManagerService == null ? null : this.sessionManagerService.getSession(callbackQuery.getMessage().getChatId());
    }

    private ChatBotSession getChatBotSession(Message message) {
        return this.sessionManagerService == null ? null : this.sessionManagerService.getSession(message.getChatId());
    }

    private void processingNotOnSession(CommandContainer commandContainer, CommandContext commandContext) {
        if (commandContainer != null) {
            commandContainer.executeFirst(commandContext);
        }

    }

    private void processingOnSession(CommandContainer commandContainer, CommandContext commandContext) {
        if (commandContainer == null) {
            commandContainer = this.commandContainerService.get(commandContext.getUserBotSession().getNameCommand(), commandContext.getUserBotSession().getTypeLastCommand());
            if (commandContainer != null) {
                commandContainer.executeOther(commandContext);
            }
        } else {
            commandContext.getUserBotSession().clear(commandContext.getName(), commandContext.getTypeCommand());
            commandContainer.executeFirst(commandContext);
        }

    }
}

