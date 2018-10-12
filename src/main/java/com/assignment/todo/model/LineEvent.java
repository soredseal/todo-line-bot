package com.assignment.todo.model;

import java.time.Instant;

public class LineEvent {
    private String type;
    private String replyToken;
    private LineEventSource source;
    private Long timestamp;
    private LineMessage message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReplyToken() {
        return replyToken;
    }

    public void setReplyToken(String replyToken) {
        this.replyToken = replyToken;
    }

    public LineEventSource getSource() {
        return source;
    }

    public void setSource(LineEventSource source) {
        this.source = source;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public LineMessage getMessage() {
        return message;
    }

    public void setMessage(LineMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\":" + (type == null ? null : "\"" + type + "\"") +
                ",\"replyToken\":" + (replyToken == null ? null : "\"" + replyToken + "\"") +
                ",\"source\":" + source +
                ",\"timestamp\":" + timestamp +
                ",\"message\":" + message +
                "}";
    }
}
