package com.assignment.todo.service;

import com.assignment.todo.model.Todo;
import com.assignment.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MongoTemplate template;

    @Autowired
    private LineService lineService;

    public Todo add(String user, String text) {
        String[] data = text.split(" : ");
        String task;
        String date;
        String time;

        try {
            task = data[0].trim();
            date = data[1].trim().replace("\n", "");
            time = data.length > 2 ? data[2].trim().replace("\n", ""):"12:00";
            if (time.length() < 5) {
                time = "0".concat(time);
            }

            if (date.equalsIgnoreCase("today")) {
                date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
            } else if (date.equalsIgnoreCase("tomorrow")) {
                date = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yy"));
            }
        } catch (Exception ex) {
            lineService.error(ex);
            return null;
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

    public List<Todo> list(String user) {
        return todoRepository.findByUser(user);
    }

    public Todo markImportant(String id, Boolean flag) {
        Query q = new Query(Criteria.where("id").is(id));
        Update u = Update
                .update("important", flag);
        return this.template.findAndModify(q, u, Todo.class);
    }
}
