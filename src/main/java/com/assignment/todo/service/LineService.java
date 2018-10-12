package com.assignment.todo.service;

import org.springframework.stereotype.Service;

@Service
public class LineService {

    public void error(Exception ex) {
        System.out.printf("Format error: %s", ex.getMessage());
    }
}
