package com.example.redis.chat.controller;

import com.example.redis.chat.publisher.MessagePublisher;
import com.example.redis.chat.subscriber.MessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessagePublisher messagePublisher;

    @Autowired
    private MessageSubscriber messageSubscriber;

    @PostMapping("/publish")
    public String publish(@RequestParam String channel, @RequestParam String message) {
        messagePublisher.publish(channel, message);
        return "Message published to channel: " + channel;
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam String channel) {
        messageSubscriber.subscribeToChannel(channel);
        return "Subscribed to channel: " + channel;
    }
}
