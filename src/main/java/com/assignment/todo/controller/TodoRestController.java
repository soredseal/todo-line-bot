package com.assignment.todo.controller;

import com.assignment.todo.model.LineRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoRestController {

    @PostMapping("/webhook")
    public ResponseEntity<String> createTodo(@RequestBody LineRequest message) {
        message.getEvents().forEach(System.out::println);
        return ResponseEntity.ok(null);
    }
}
