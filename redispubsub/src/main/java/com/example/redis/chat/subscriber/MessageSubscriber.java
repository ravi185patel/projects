package com.example.redis.chat.subscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MessageSubscriber {


    @Autowired
    private RedisMessageListenerContainer messageListenerContainer;

//    @Override
//    public void onMessage(Message message, byte[] pattern) {
//        String channel = new String(message.getChannel());
//        String body = new String(message.getBody());
//        System.out.println("Received message: " + body + " from channel: " + channel);
//    }

    public void subscribeToChannel(String channel) {
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                String channelName = new String(message.getChannel());
                String body = new String(message.getBody());
                System.out.println("Received message: " + body + " from channel: " + channelName);
            }
        });

        // Add the listener to the container for the specified channel
        messageListenerContainer.addMessageListener(listenerAdapter, new PatternTopic(channel));
        System.out.println("Subscribed to channel: " + channel);
    }
}
