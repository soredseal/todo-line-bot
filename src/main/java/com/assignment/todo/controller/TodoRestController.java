package com.assignment.todo.controller;

import com.assignment.todo.model.LineRequest;
import com.assignment.todo.model.Todo;
import com.assignment.todo.model.UpdateTodo;
import com.assignment.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoRestController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/webhook")
    public ResponseEntity<String> createTodo(@RequestBody LineRequest message) {
        message.getEvents().forEach(lineEvent -> todoService.add(lineEvent.getSource().getUserId(), lineEvent.getMessage().getText()));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/list/{user}")
    public ResponseEntity<List<Todo>> listTodo(@PathVariable("user") String user) {
        return ResponseEntity.ok(todoService.list(user));
    }

    @PutMapping("/important")
    public ResponseEntity<String> updateImportant(@RequestBody UpdateTodo update) {
        todoService.markImportant(update.getId(), update.getFlag());
        return ResponseEntity.ok(null);
    }
}
