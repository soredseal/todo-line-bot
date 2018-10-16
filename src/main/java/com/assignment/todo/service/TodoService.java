package com.assignment.todo.service;

import com.assignment.todo.model.Todo;
import com.assignment.todo.model.TodoSummary;
import com.assignment.todo.model.TodoSummaryInfo;
import com.assignment.todo.model.TodoSummaryList;
import com.assignment.todo.repository.TodoRepository;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
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
            time = data.length > 2 ? data[2].trim().replace("\n", "") : "12:00";
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
        return todoRepository.findByUser(user, Sort.by(Sort.Order.desc("important"), Sort.Order.asc("time")));
    }

    public List<Todo> markImportant(String user, String id, Boolean flag) {
        Query q = new Query(Criteria.where("id").is(id));
        Update u = Update
                .update("important", flag);
        this.template.findAndModify(q, u, Todo.class);
        return list(user);
    }

    public List<Todo> markCompleted(String user, String id, Boolean flag) {
        Query q = new Query(Criteria.where("id").is(id));
        Update u = Update
                .update("completed", flag);
        this.template.findAndModify(q, u, Todo.class);
        return list(user);
    }

    @Scheduled(cron="0 17 12,18,20 * * *", zone="Asia/Bangkok")
    public void summary() {
        TypedAggregation<Todo> taskAggregation = Aggregation.newAggregation(Todo.class,
                                                            Aggregation.match(Criteria.where("time").gte(LocalDate.now())),
                                                            Aggregation.group("user", "completed")
                                                                    .last("user").as("user")
                                                                    .last("completed").as("completed")
                                                                    .count().as("tasks"),
                                                            Aggregation.group("user")
                                                                    .push(new BasicDBObject
                                                                            ("completed", "$completed").append
                                                                            ("tasks", "$tasks")).as("info"),
                                                            Aggregation.project("info")
                                                                    .and("user").previousOperation());

        AggregationResults<TodoSummary> results = template.
                aggregate(taskAggregation, TodoSummary.class);

        List<TodoSummary> todoSummaryList = results.getMappedResults();
        this.lineService.pushMessage(todoSummaryList);

    }
}
