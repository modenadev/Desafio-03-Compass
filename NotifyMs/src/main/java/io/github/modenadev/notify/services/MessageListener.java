package io.github.modenadev.notify.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.modenadev.notify.domain.Notify;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "notify")
    public void listen(@Payload String message) {
        System.out.println("Recivied message: " + message);

        try {
            var mapper = new ObjectMapper();
            Notify notify = mapper.readValue(message, Notify.class);
            // Faça algo com o notify

        } catch (JsonProcessingException e) {
            System.err.println("Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
