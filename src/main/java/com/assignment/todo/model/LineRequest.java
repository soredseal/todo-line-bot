package com.assignment.todo.model;

import java.util.List;

public class LineRequest {
    private List<LineEvent> events;

    public List<LineEvent> getEvents() {
        return events;
    }

    public void setEvents(List<LineEvent> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "{" +
                "\"events\":" + events +
                "}";
    }
}
