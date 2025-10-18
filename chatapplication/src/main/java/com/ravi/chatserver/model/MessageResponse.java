package com.ravi.chatserver.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private String messageType;
    private LocalDateTime timestamp;

    // Constructors, Getters and Setters
}
