package com.assignment.todo.controller;

import com.assignment.todo.model.LineRequest;
import com.assignment.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
