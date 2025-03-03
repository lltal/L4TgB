package com.github.lltal.filler.starter.callback;

public class CallbackDataSender {
    private String text;
    private CallbackData data;

    public String getText() {
        return this.text;
    }

    public CallbackData getData() {
        return this.data;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public void setData(final CallbackData data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CallbackDataSender)) {
            return false;
        } else {
            CallbackDataSender other = (CallbackDataSender)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$text = this.getText();
                Object other$text = other.getText();
                if (this$text == null) {
                    if (other$text != null) {
                        return false;
                    }
                } else if (!this$text.equals(other$text)) {
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
        return other instanceof CallbackDataSender;
    }

    public int hashCode() {
        int result = 1;
        Object $text = this.getText();
        result = result * 59 + ($text == null ? 43 : $text.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        String var10000 = this.getText();
        return "CallbackDataSender(text=" + var10000 + ", data=" + this.getData() + ")";
    }

    public CallbackDataSender(final String text, final CallbackData data) {
        this.text = text;
        this.data = data;
    }

    public CallbackDataSender() {
    }
}
