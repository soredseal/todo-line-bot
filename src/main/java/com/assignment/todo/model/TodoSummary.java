package com.assignment.todo.model;

import java.util.Arrays;

public class TodoSummary {
    private String user;
    private TodoSummaryInfo[] info;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public TodoSummaryInfo[] getInfo() {
        return info;
    }

    public void setInfo(TodoSummaryInfo[] info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "TodoSummary{" +
                "user='" + user + '\'' +
                ", info=" + Arrays.toString(info) +
                '}';
    }
}
