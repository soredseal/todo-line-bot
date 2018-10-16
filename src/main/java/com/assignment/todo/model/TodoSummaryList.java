package com.assignment.todo.model;

import java.util.Arrays;

public class TodoSummaryList {
    private TodoSummary[] todoSummary;

    public TodoSummary[] getTodoSummary() {
        return todoSummary;
    }

    public void setTodoSummary(TodoSummary[] todoSummary) {
        this.todoSummary = todoSummary;
    }

    @Override
    public String toString() {
        return "TodoSummaryList{" +
                "todoSummary=" + Arrays.toString(todoSummary) +
                '}';
    }
}
