package com.assignment.todo.service;

import com.assignment.todo.model.Todo;
import com.assignment.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo add(String user, String text) {
        Todo todo = new Todo();
        todo.setUser(user);
        todo.setTask(text);
        return todoRepository.save(todo);
    }

}
