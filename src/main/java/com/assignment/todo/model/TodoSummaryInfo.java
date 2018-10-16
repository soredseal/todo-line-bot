package com.assignment.todo.model;

public class TodoSummaryInfo {
    private Integer tasks;
    private Boolean completed;

    public Integer getTasks() {
        return tasks;
    }

    public void setTasks(Integer tasks) {
        this.tasks = tasks;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TodoSummaryInfo{" +
                "tasks=" + tasks +
                ", completed=" + completed +
                '}';
    }
}

