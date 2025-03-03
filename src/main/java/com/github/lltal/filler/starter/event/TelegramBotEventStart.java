package com.github.lltal.filler.starter.event;

public class TelegramBotEventStart {
    public String toString() {
        return "TelegramBotEventStart()";
    }

    public TelegramBotEventStart() {
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TelegramBotEventStart)) {
            return false;
        } else {
            TelegramBotEventStart other = (TelegramBotEventStart)o;
            return other.canEqual(this);
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TelegramBotEventStart;
    }

    public int hashCode() {
        return 1;
    }
}
