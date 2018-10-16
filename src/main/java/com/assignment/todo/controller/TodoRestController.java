package com.assignment.todo.controller;

import com.assignment.todo.model.LineRequest;
import com.assignment.todo.model.Todo;
import com.assignment.todo.model.UpdateTodo;
import com.assignment.todo.service.LineService;
import com.assignment.todo.service.TodoService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoRestController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private LineService lineService;

    @Value("${channel_secret}")
    private String channelSecret;

    @PostMapping("/webhook")
    public ResponseEntity<String> createTodo(@RequestHeader(value = "X-Line-Signature") String signature, @RequestBody LineRequest message) {
        if (!validateSignature(signature, message.toString())) {
            lineService.error(new Exception("Invalid signature"));
            return ResponseEntity.ok(null);
        }
        message.getEvents().forEach(lineEvent -> todoService.add(lineEvent.getSource().getUserId(), lineEvent.getMessage().getText()));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/list/{user}")
    public ResponseEntity<List<Todo>> listTodo(@PathVariable("user") String user) {
        return ResponseEntity.ok(todoService.list(user));
    }

    @PutMapping("/important/{user}")
    public ResponseEntity<List<Todo>> updateImportant(@PathVariable("user") String user, @RequestBody UpdateTodo update) {
        return ResponseEntity.ok(todoService.markImportant(user, update.getId(), update.getFlag()));
    }

    @PutMapping("/completed/{user}")
    public ResponseEntity<List<Todo>> updateCompletes(@PathVariable("user") String user, @RequestBody UpdateTodo update) {
        return ResponseEntity.ok(todoService.markCompleted(user, update.getId(), update.getFlag()));
    }

    private Boolean validateSignature(String header, String body) {
        String channelSecret = this.channelSecret;
        String httpRequestBody = body;
        SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
        String signature;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(key);
            byte[] source = httpRequestBody.getBytes("UTF-8");
            signature = Base64.encodeBase64String(mac.doFinal(source));
        } catch (Exception ex) {
            return false;
        }
        return signature.equals(header);
    }
}
