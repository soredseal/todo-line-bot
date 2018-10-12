package com.assignment.todo.model;

public class LineMessage {
    private String type;
    private String id;
    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{" +
                "\"type\":" + (type == null ? null : "\"" + type + "\"") +
                ",\"id\":" + (id == null ? null : "\"" + id + "\"") +
                ",\"text\":" + (text == null ? null : "\"" + text + "\"") +
                "}";
    }
}
