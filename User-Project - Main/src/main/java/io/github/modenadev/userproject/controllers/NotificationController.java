package io.github.modenadev.userproject.controllers;

import io.github.modenadev.userproject.services.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(@RequestBody String message) {
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("Hello RabbitMQ!!!");
    }
}
