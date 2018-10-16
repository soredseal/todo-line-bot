package com.assignment.todo.repository;

import com.assignment.todo.model.Todo;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {
    List<Todo> findByUser(String user, Sort sort);
}
