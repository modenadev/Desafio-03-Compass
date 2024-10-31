package io.github.modenadev.userproject.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "notify")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
