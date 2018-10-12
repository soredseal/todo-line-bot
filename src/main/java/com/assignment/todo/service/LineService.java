package com.assignment.todo.service;

import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;

@Service
public class LineService {

    public void dateFormatError(DateTimeParseException ex) {
        System.out.printf("Date format error: %s", ex.getParsedString());
    }
}
