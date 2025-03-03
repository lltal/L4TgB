package com.github.lltal.filler.starter.command;

import com.github.lltal.filler.starter.TelegramLongPollingEngine;
import com.github.lltal.filler.starter.session.ChatBotSession;
import com.github.lltal.filler.starter.session.UserBotSession;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandContext {
    private String name;
    private Object data;
    private TypeCommand typeCommand;
    private TelegramLongPollingEngine engine;
    private Update update;
    private UserBotSession userBotSession;
    private ChatBotSession chatBotSession;

    public CommandContext() {
    }

    public String getName() {
        return this.name;
    }

    public Object getData() {
        return this.data;
    }

    public TypeCommand getTypeCommand() {
        return this.typeCommand;
    }

    public TelegramLongPollingEngine getEngine() {
        return this.engine;
    }

    public Update getUpdate() {
        return this.update;
    }

    public UserBotSession getUserBotSession() {
        return this.userBotSession;
    }

    public ChatBotSession getChatBotSession() {
        return this.chatBotSession;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public void setTypeCommand(final TypeCommand typeCommand) {
        this.typeCommand = typeCommand;
    }

    public void setEngine(final TelegramLongPollingEngine engine) {
        this.engine = engine;
    }

    public void setUpdate(final Update update) {
        this.update = update;
    }

    public void setUserBotSession(final UserBotSession userBotSession) {
        this.userBotSession = userBotSession;
    }

    public void setChatBotSession(final ChatBotSession chatBotSession) {
        this.chatBotSession = chatBotSession;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CommandContext)) {
            return false;
        } else {
            CommandContext other = (CommandContext)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label95: {
                    Object this$name = this.getName();
                    Object other$name = other.getName();
                    if (this$name == null) {
                        if (other$name == null) {
                            break label95;
                        }
                    } else if (this$name.equals(other$name)) {
                        break label95;
                    }

                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$typeCommand = this.getTypeCommand();
                Object other$typeCommand = other.getTypeCommand();
                if (this$typeCommand == null) {
                    if (other$typeCommand != null) {
                        return false;
                    }
                } else if (!this$typeCommand.equals(other$typeCommand)) {
                    return false;
                }

                label74: {
                    Object this$engine = this.getEngine();
                    Object other$engine = other.getEngine();
                    if (this$engine == null) {
                        if (other$engine == null) {
                            break label74;
                        }
                    } else if (this$engine.equals(other$engine)) {
                        break label74;
                    }

                    return false;
                }

                label67: {
                    Object this$update = this.getUpdate();
                    Object other$update = other.getUpdate();
                    if (this$update == null) {
                        if (other$update == null) {
                            break label67;
                        }
                    } else if (this$update.equals(other$update)) {
                        break label67;
                    }

                    return false;
                }

                Object this$userBotSession = this.getUserBotSession();
                Object other$userBotSession = other.getUserBotSession();
                if (this$userBotSession == null) {
                    if (other$userBotSession != null) {
                        return false;
                    }
                } else if (!this$userBotSession.equals(other$userBotSession)) {
                    return false;
                }

                Object this$chatBotSession = this.getChatBotSession();
                Object other$chatBotSession = other.getChatBotSession();
                if (this$chatBotSession == null) {
                    if (other$chatBotSession != null) {
                        return false;
                    }
                } else if (!this$chatBotSession.equals(other$chatBotSession)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CommandContext;
    }

    public int hashCode() {
        int result = 1;
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $typeCommand = this.getTypeCommand();
        result = result * 59 + ($typeCommand == null ? 43 : $typeCommand.hashCode());
        Object $engine = this.getEngine();
        result = result * 59 + ($engine == null ? 43 : $engine.hashCode());
        Object $update = this.getUpdate();
        result = result * 59 + ($update == null ? 43 : $update.hashCode());
        Object $userBotSession = this.getUserBotSession();
        result = result * 59 + ($userBotSession == null ? 43 : $userBotSession.hashCode());
        Object $chatBotSession = this.getChatBotSession();
        result = result * 59 + ($chatBotSession == null ? 43 : $chatBotSession.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getName();
        return "CommandContext(name=" + var10000 + ", data=" + this.getData() + ", typeCommand=" + this.getTypeCommand() + ", engine=" + this.getEngine() + ", update=" + this.getUpdate() + ", userBotSession=" + this.getUserBotSession() + ", chatBotSession=" + this.getChatBotSession() + ")";
    }
}

