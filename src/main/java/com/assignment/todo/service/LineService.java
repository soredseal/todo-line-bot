package com.assignment.todo.service;

import com.assignment.todo.model.LinePushMessage;
import com.assignment.todo.model.LineReplyMessage;
import com.assignment.todo.model.TodoSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LineService {

    private RestTemplate rest = new RestTemplate();

    public void error(Exception ex) {
        System.out.printf("Format error: %s", ex.getMessage());
    }

    @Value("${channel_access_key}")
    private String channelSecret;

    public void pushMessage(List<TodoSummary> summaries) {
        summaries.forEach(summary -> {
            LinePushMessage pushMessage = new LinePushMessage(summary);
            HttpHeaders headers =  new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Authorization", "Bearer " + channelSecret);
            HttpEntity<LinePushMessage> request = new HttpEntity<>(pushMessage, headers);

            rest.postForEntity("https://api.line.me/v2/bot/message/push", request, null);
        });
    }

    public void reply(String token, List<String> messages) {
        HttpHeaders headers =  new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + channelSecret);

        LineReplyMessage replyMessage = new LineReplyMessage(token, messages);
        HttpEntity<LineReplyMessage> request = new HttpEntity<>(replyMessage, headers);

        rest.postForEntity("https://api.line.me/v2/bot/message/reply", request, null);
    }
}
