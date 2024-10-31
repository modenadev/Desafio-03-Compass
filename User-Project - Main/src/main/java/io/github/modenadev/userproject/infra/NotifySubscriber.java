package io.github.modenadev.userproject.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.modenadev.userproject.model.Notify;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotifySubscriber {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueNotify;

    public NotifySubscriber(RabbitTemplate rabbitTemplate , Queue queueNotify) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueNotify = queueNotify;

    }


    public void solicitNotify(Notify notify) throws JsonProcessingException {
        var json = convertIntoJson(notify);
        rabbitTemplate.convertAndSend(queueNotify.getName(), json);
    }

    private String convertIntoJson(Notify notify) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(notify);
        return json;

    }

}
