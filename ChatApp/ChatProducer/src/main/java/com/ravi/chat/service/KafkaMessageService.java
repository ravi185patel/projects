package com.ravi.chat.service;

import com.ravi.chat.model.MessageDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.name:message}")
    private String topicName;

    public KafkaMessageService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String messageId) {
        kafkaTemplate.send(topicName, messageId);
    }
}
