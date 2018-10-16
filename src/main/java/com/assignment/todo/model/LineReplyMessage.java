package com.assignment.todo.model;

import java.util.List;
import java.util.stream.Collectors;

public class LineReplyMessage {
    private String replyToken;
    private List<LineMessage> messages;

    public LineReplyMessage(String token, List<String> messages) {
        this.replyToken = token;

        List<LineMessage> lineMessages = messages.stream().map(message -> {
            LineMessage msg = new LineMessage();
            msg.setType("text");
            msg.setText(message);
            return msg;
        }).collect(Collectors.toList());

        this.messages = lineMessages;
    }

    public String getReplyToken() {
        return replyToken;
    }

    public void setReplyToken(String replyToken) {
        this.replyToken = replyToken;
    }

    public List<LineMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<LineMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "LineReplyMessage{" +
                "replyToken='" + replyToken + '\'' +
                ", messages=" + messages +
                '}';
    }
}
