package com.chat.websocketserver.handler;

import com.chat.websocketserver.publisher.RedisMessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class); // Replace with your class name

    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    private final RedisMessagePublisher redisMessagePublisher; // Inject your Redis publisher

    public MyWebSocketHandler(RedisMessagePublisher redisMessagePublisher) {
        this.redisMessagePublisher = redisMessagePublisher;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        userSessions.put(userId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        redisMessagePublisher.publish("chat", message.getPayload()); // publish to Redis
    }

    public void sendToAll(String message) {
        for (WebSocketSession session : userSessions.values()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException ignored) {}
        }
    }

    private String getUserId(WebSocketSession session) {
        String query = session.getUri().getQuery(); // e.g., userId=123
        return Arrays.stream(query.split("&"))
                .filter(p -> p.startsWith("userId="))
                .map(p -> p.split("=")[1])
                .findFirst()
                .orElse("unknown");
    }
}