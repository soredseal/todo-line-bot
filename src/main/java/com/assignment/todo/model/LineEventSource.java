package com.assignment.todo.model;

public class LineEventSource {
    private String userId;
    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "\"userId\":" + (userId == null ? null : "\"" + userId + "\"") +
                ",\"type\":" + (type == null ? null : "\"" + type + "\"") +
                "}";
    }
}
