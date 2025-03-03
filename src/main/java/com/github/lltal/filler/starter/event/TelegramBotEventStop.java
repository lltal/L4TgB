package com.github.lltal.filler.starter.event;
public class TelegramBotEventStop {
    public String toString() {
        return "TelegramBotEventStop()";
    }

    public TelegramBotEventStop() {
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TelegramBotEventStop)) {
            return false;
        } else {
            TelegramBotEventStop other = (TelegramBotEventStop)o;
            return other.canEqual(this);
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TelegramBotEventStop;
    }

    public int hashCode() {
        return 1;
    }
}
