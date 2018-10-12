package com.assignment.todo.service;

import com.assignment.todo.model.Todo;
import com.assignment.todo.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private TodoService todoService;

    @Test
    public void addTodoWithDateTime() {
        String user = "user";
        String text = "Buy milk : 20/10/18 : 13:00";

        Todo expected = new Todo();
        expected.setUser(user);
        expected.setTask("Buy milk");
        expected.setCompleted(false);
        expected.setImportant(false);
        expected.setTime(LocalDateTime.parse("20/10/2018 13:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

        todoService.add(user, text);
        verify(todoRepository).save(ArgumentMatchers.refEq(expected));
    }

    @Test
    public void addTodoWithTodayTime() {
        String user = "user";
        String text =  "Finish writing shopping list : today : 15:30";

        Todo expected = new Todo();
        expected.setUser(user);
        expected.setTask("Finish writing shopping list");
        expected.setCompleted(false);
        expected.setImportant(false);
        expected.setTime(LocalDate.now().atTime(15, 30));

        todoService.add(user, text);
        verify(todoRepository).save(ArgumentMatchers.refEq(expected));
    }

    @Test
    public void addTodoWithTomorrowTime() {
        String user = "user";
        String text =  "Watch movie : tomorrow : 18:00";

        Todo expected = new Todo();
        expected.setUser(user);
        expected.setTask("Watch movie");
        expected.setCompleted(false);
        expected.setImportant(false);
        expected.setTime(LocalDate.now().plusDays(1).atTime(18, 0));

        todoService.add(user, text);
        verify(todoRepository).save(ArgumentMatchers.refEq(expected));
    }
}