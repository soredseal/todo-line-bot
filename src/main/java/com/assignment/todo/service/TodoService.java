package com.assignment.todo.service;

import com.assignment.todo.model.Todo;
import com.assignment.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private LineService lineService;

    public Todo add(String user, String text) {
        String[] data = text.split(" : ");
        String task = data[0];
        String date = data[1];
        String time = data[2];
        try {
            if (data[1].equalsIgnoreCase("today")) {
                date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
            } else if (data[1].equalsIgnoreCase("tomorrow")) {
                date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yy"));
            }
        } catch (DateTimeParseException ex) {
            lineService.dateFormatError(ex);
        }
        String dateTime = String.join(" ", date, time);

        Todo todo = new Todo();
        todo.setUser(user);
        todo.setTask(task);
        todo.setTime(LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")));
        todo.setImportant(false);
        todo.setCompleted(false);

        return todoRepository.save(todo);
    }

}
