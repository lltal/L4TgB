package com.github.lltal.filler.starter.command;

import com.github.lltal.filler.starter.TelegramLongPollingEngine;
import com.github.lltal.filler.starter.annotation.CommandFirst;
import com.github.lltal.filler.starter.annotation.CommandOther;
import com.github.lltal.filler.starter.annotation.ParamName;
import com.github.lltal.filler.starter.session.ChatBotSession;
import com.github.lltal.filler.starter.session.UserBotSession;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandContainer {
    private final String[] names;
    private final TypeCommand typeCommand;
    private Object objectController;
    private Method commandFirst;
    private Method commandOther;

    public static String castCommandNameAndType(String command, TypeCommand typeCommand) {
        return command + "-" + typeCommand;
    }

    public CommandContainer(String[] names, TypeCommand typeCommand) {
        this.names = names;
        this.typeCommand = typeCommand;
    }

    public List<String> getNamesForManager() {
        return (List) Arrays.stream(this.names).map((s) -> {
            return castCommandNameAndType(s, this.typeCommand);
        }).collect(Collectors.toList());
    }

    public void executeFirst(CommandContext commandContext) {
        Method[] declaredMethods = this.objectController.getClass().getDeclaredMethods();
        Method[] var3 = declaredMethods;
        int var4 = declaredMethods.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Method declaredMethod = var3[var5];
            if (declaredMethod.isAnnotationPresent(CommandFirst.class)) {
                this.executeMethod(declaredMethod, commandContext);
            }
        }

    }

    public void executeOther(CommandContext commandContext) {
        Method[] declaredMethods = this.objectController.getClass().getDeclaredMethods();
        Method[] var3 = declaredMethods;
        int var4 = declaredMethods.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Method declaredMethod = var3[var5];
            if (declaredMethod.isAnnotationPresent(CommandOther.class)) {
                this.executeMethod(declaredMethod, commandContext);
            }
        }

    }

    private void executeMethod(Method method, CommandContext commandContext) {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for(int i = 0; i < parameters.length; ++i) {
            Parameter param = parameters[i];
            Object data = this.getObjectParameter(param, commandContext);
            args[i] = data;
        }

        try {
            method.invoke(this.objectController, args);
        } catch (InvocationTargetException | IllegalAccessException var8) {
            throw new RuntimeException(var8);
        }
    }

    private Object getObjectParameter(Parameter param, CommandContext commandContext) {
        Update update = commandContext.getUpdate();
        Class<?> type = param.getType();
        if (type == TelegramLongPollingEngine.class) {
            return commandContext.getEngine();
        } else if (type == CommandContext.class) {
            return commandContext;
        } else if (type == Update.class) {
            return update;
        } else if (type == Message.class) {
            return update.getMessage();
        } else if (type == CallbackQuery.class) {
            return update.getCallbackQuery();
        } else if (type == Chat.class) {
            return update.getMessage().getChat();
        } else if (type == Long.class) {
            return this.getObjectLongParameter(param, commandContext);
        } else if (type == ChatBotSession.class) {
            return commandContext.getChatBotSession();
        } else {
            return type == UserBotSession.class ? commandContext.getUserBotSession() : null;
        }
    }

    private Object getObjectLongParameter(Parameter param, CommandContext commandContext) {
        Update update = commandContext.getUpdate();
        ParamName annotation = (ParamName)param.getAnnotation(ParamName.class);
        String name;
        if (annotation != null) {
            name = annotation.value();
        } else {
            name = param.getName();
        }

        if (update.hasMessage()) {
            return this.processingParamForMessage(name, commandContext);
        } else {
            return update.hasCallbackQuery() ? this.processingParamForCallback(name, commandContext) : null;
        }
    }

    private Object processingParamForCallback(String name, CommandContext commandContext) {
        Update update = commandContext.getUpdate();
        CallbackQuery callbackQuery = update.getCallbackQuery();
        switch (name) {
            case "userId":
                return callbackQuery.getFrom().getId();
            case "chatId":
                return callbackQuery.getMessage().getChatId();
            case "messageId":
                return (long)callbackQuery.getMessage().getMessageId();
            default:
                return null;
        }
    }

    private Object processingParamForMessage(String name, CommandContext commandContext) {
        Update update = commandContext.getUpdate();
        Message message = update.getMessage();
        switch (name) {
            case "userId":
                return message.getFrom().getId();
            case "chatId":
                return message.getChatId();
            case "messageId":
                return (long)message.getMessageId();
            default:
                return null;
        }
    }

    public String[] getNames() {
        return this.names;
    }

    public TypeCommand getTypeCommand() {
        return this.typeCommand;
    }

    public Object getObjectController() {
        return this.objectController;
    }

    public Method getCommandFirst() {
        return this.commandFirst;
    }

    public Method getCommandOther() {
        return this.commandOther;
    }

    public void setObjectController(final Object objectController) {
        this.objectController = objectController;
    }

    public void setCommandFirst(final Method commandFirst) {
        this.commandFirst = commandFirst;
    }

    public void setCommandOther(final Method commandOther) {
        this.commandOther = commandOther;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CommandContainer)) {
            return false;
        } else {
            CommandContainer other = (CommandContainer)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!Arrays.deepEquals(this.getNames(), other.getNames())) {
                return false;
            } else {
                label61: {
                    Object this$typeCommand = this.getTypeCommand();
                    Object other$typeCommand = other.getTypeCommand();
                    if (this$typeCommand == null) {
                        if (other$typeCommand == null) {
                            break label61;
                        }
                    } else if (this$typeCommand.equals(other$typeCommand)) {
                        break label61;
                    }

                    return false;
                }

                label54: {
                    Object this$objectController = this.getObjectController();
                    Object other$objectController = other.getObjectController();
                    if (this$objectController == null) {
                        if (other$objectController == null) {
                            break label54;
                        }
                    } else if (this$objectController.equals(other$objectController)) {
                        break label54;
                    }

                    return false;
                }

                Object this$commandFirst = this.getCommandFirst();
                Object other$commandFirst = other.getCommandFirst();
                if (this$commandFirst == null) {
                    if (other$commandFirst != null) {
                        return false;
                    }
                } else if (!this$commandFirst.equals(other$commandFirst)) {
                    return false;
                }

                Object this$commandOther = this.getCommandOther();
                Object other$commandOther = other.getCommandOther();
                if (this$commandOther == null) {
                    if (other$commandOther != null) {
                        return false;
                    }
                } else if (!this$commandOther.equals(other$commandOther)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CommandContainer;
    }

    public int hashCode() {
        int result = 1;
        result = result * 59 + Arrays.deepHashCode(this.getNames());
        Object $typeCommand = this.getTypeCommand();
        result = result * 59 + ($typeCommand == null ? 43 : $typeCommand.hashCode());
        Object $objectController = this.getObjectController();
        result = result * 59 + ($objectController == null ? 43 : $objectController.hashCode());
        Object $commandFirst = this.getCommandFirst();
        result = result * 59 + ($commandFirst == null ? 43 : $commandFirst.hashCode());
        Object $commandOther = this.getCommandOther();
        result = result * 59 + ($commandOther == null ? 43 : $commandOther.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = Arrays.deepToString(this.getNames());
        return "CommandContainer(names=" + var10000 + ", typeCommand=" + this.getTypeCommand() + ", objectController=" + this.getObjectController() + ", commandFirst=" + this.getCommandFirst() + ", commandOther=" + this.getCommandOther() + ")";
    }
}

