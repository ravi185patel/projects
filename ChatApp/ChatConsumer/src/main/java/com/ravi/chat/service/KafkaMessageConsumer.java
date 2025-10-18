package com.ravi.chat.service;

import com.ravi.chat.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class KafkaMessageConsumer {

    @Autowired
    private MessageService messageService;

    @KafkaListener(topics = "${kafka.topic.name:message}", groupId = "my-consumer-group")
    public void consume(String messageId) {
        System.out.println("Received message: " + messageId);
        MessageResponse messageResponse = messageService.retrieveMessage(messageId);
        if(Objects.nonNull(messageResponse)) {
            System.out.println("Received message: " + messageResponse.toString());
        }
    }
}
