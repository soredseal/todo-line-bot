package com.assignment.todo.model;

import java.util.ArrayList;
import java.util.List;

public class LinePushMessage {
    private String to;
    private List<LineMessage> messages = new ArrayList<>();

    public LinePushMessage(TodoSummary summary) {
        this.to = summary.getUser();
        Integer completed = 0;
        Integer incomplete = 0;
        for (TodoSummaryInfo info:
             summary.getInfo()) {
            if (info.getCompleted()) {
                completed = info.getTasks();
            } else {
                incomplete = info.getTasks();
            }
        }

        String message = "You've got " + incomplete + " tasks left to complete and " + completed + " tasks have already done";
        LineMessage msg = new LineMessage();
        msg.setType("text");
        msg.setText(message);
        this.messages.add(msg);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<LineMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<LineMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "LinePushMessage{" +
                "to='" + to + '\'' +
                ", messages=" + messages +
                '}';
    }
}
