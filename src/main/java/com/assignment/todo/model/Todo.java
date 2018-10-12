package com.assignment.todo.model;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class Todo {
    private ObjectId id;
    private String user;
    private String task;
    private LocalDateTime time;
    private Boolean completed;
    private Boolean important;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getImportant() {
        return important;
    }

    public void setImportant(Boolean important) {
        this.important = important;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"user\":" + (user == null ? null : "\"" + user + "\"") +
                ",\"task\":" + (task == null ? null : "\"" + task + "\"") +
                ",\"time\":" + time +
                ",\"completed\":" + completed +
                ",\"important\":" + important +
                "}";
    }
}
