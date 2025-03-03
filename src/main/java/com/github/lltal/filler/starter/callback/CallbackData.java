package com.github.lltal.filler.starter.callback;

public class CallbackData {
    private String command;
    private String data;

    public CallbackData(String command) {
        this.command = command;
        this.data = "";
    }

    public String getCommand() {
        return this.command;
    }

    public String getData() {
        return this.data;
    }

    public void setCommand(final String command) {
        this.command = command;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CallbackData)) {
            return false;
        } else {
            CallbackData other = (CallbackData)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$command = this.getCommand();
                Object other$command = other.getCommand();
                if (this$command == null) {
                    if (other$command != null) {
                        return false;
                    }
                } else if (!this$command.equals(other$command)) {
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

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CallbackData;
    }

    public int hashCode() {
        int result = 1;
        Object $command = this.getCommand();
        result = result * 59 + ($command == null ? 43 : $command.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getCommand();
        return "CallbackData(command=" + var10000 + ", data=" + this.getData() + ")";
    }

    public CallbackData(final String command, final String data) {
        this.command = command;
        this.data = data;
    }

    public CallbackData() {
    }
}

