package com.github.lltal.filler.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@ConfigurationProperties("telegram.bot")
public class TelegramBotProperty {
    private static final Logger log = LoggerFactory.getLogger(TelegramBotProperty.class);
    private String username;
    private String token;
    private String configFile;
    private Session session;

    public TelegramBotProperty() {
    }

    @PostConstruct
    public void postInit() {
        log.info("Загружена инфа по данным бота: @" + this.username);
        log.info("Сущность: " + this);
    }

    public String getUsername() {
        return this.username;
    }

    public String getToken() {
        return this.token;
    }

    public String getConfigFile() {
        return this.configFile;
    }

    public Session getSession() {
        return this.session;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public void setConfigFile(final String configFile) {
        this.configFile = configFile;
    }

    public void setSession(final Session session) {
        this.session = session;
    }

    public String toString() {
        String var10000 = this.getUsername();
        return "TelegramBotProperty(username=" + var10000 + ", token=" + this.getToken() + ", configFile=" + this.getConfigFile() + ", session=" + this.getSession() + ")";
    }

    public static class Session {
        private TimeLife timeLife;
        private boolean enable;

        public Session() {
        }

        public TimeLife getTimeLife() {
            return this.timeLife;
        }

        public boolean isEnable() {
            return this.enable;
        }

        public void setTimeLife(final TimeLife timeLife) {
            this.timeLife = timeLife;
        }

        public void setEnable(final boolean enable) {
            this.enable = enable;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Session)) {
                return false;
            } else {
                Session other = (Session)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (this.isEnable() != other.isEnable()) {
                    return false;
                } else {
                    Object this$timeLife = this.getTimeLife();
                    Object other$timeLife = other.getTimeLife();
                    if (this$timeLife == null) {
                        if (other$timeLife != null) {
                            return false;
                        }
                    } else if (!this$timeLife.equals(other$timeLife)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Session;
        }

        public int hashCode() {
            int result = 1;
            result = result * 59 + (this.isEnable() ? 79 : 97);
            Object $timeLife = this.getTimeLife();
            result = result * 59 + ($timeLife == null ? 43 : $timeLife.hashCode());
            return result;
        }

        public String toString() {
            TimeLife var10000 = this.getTimeLife();
            return "TelegramBotProperty.Session(timeLife=" + var10000 + ", enable=" + this.isEnable() + ")";
        }
    }

    public static class TimeLife {
        private int chat;
        private int user;

        public TimeLife() {
        }

        public int getChat() {
            return this.chat;
        }

        public int getUser() {
            return this.user;
        }

        public void setChat(final int chat) {
            this.chat = chat;
        }

        public void setUser(final int user) {
            this.user = user;
        }

        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof TimeLife)) {
                return false;
            } else {
                TimeLife other = (TimeLife)o;
                if (!other.canEqual(this)) {
                    return false;
                } else if (this.getChat() != other.getChat()) {
                    return false;
                } else {
                    return this.getUser() == other.getUser();
                }
            }
        }

        protected boolean canEqual(final Object other) {
            return other instanceof TimeLife;
        }

        public int hashCode() {
            int result = 1;
            result = result * 59 + this.getChat();
            result = result * 59 + this.getUser();
            return result;
        }

        public String toString() {
            int var10000 = this.getChat();
            return "TelegramBotProperty.TimeLife(chat=" + var10000 + ", user=" + this.getUser() + ")";
        }
    }
}
