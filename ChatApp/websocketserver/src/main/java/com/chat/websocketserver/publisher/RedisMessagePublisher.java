package com.chat.websocketserver.publisher;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher {

//    private final RedisTemplate<String, Object> redisTemplate;
//    private final ChannelTopic messageTopic; // Define your Redis channel
//
//    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic messageTopic) {
//        this.redisTemplate = redisTemplate;
//        this.messageTopic = messageTopic;
//    }
//
//    public void publish(String message) {
//        redisTemplate.convertAndSend(messageTopic.getTopic(), message);
//        System.out.println("Published message to Redis channel: " + messageTopic.getTopic());
//    }

    private final StringRedisTemplate template;

    public RedisMessagePublisher(StringRedisTemplate template) {
        this.template = template;
    }

    public void publish(String topic, String message) {
        template.convertAndSend(topic, message);
    }
}