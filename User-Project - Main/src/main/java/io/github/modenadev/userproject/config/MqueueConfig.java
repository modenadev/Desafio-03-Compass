package io.github.modenadev.userproject.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqueueConfig {

    @Value("${mq.queues.notify}")
    private String notifyQueue;

    @Bean
    public Queue notifyQueue() {
        return new Queue(notifyQueue, true);
    }
}
