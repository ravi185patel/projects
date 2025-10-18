package com.chat.websocketserver.listener;

import com.chat.websocketserver.handler.MyWebSocketHandler;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class RedisMessageListener implements MessageListener {

    private final MyWebSocketHandler socketHandler;

    public RedisMessageListener(MyWebSocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        socketHandler.sendToAll(msg); // broadcast to WebSocket clients
    }
}
