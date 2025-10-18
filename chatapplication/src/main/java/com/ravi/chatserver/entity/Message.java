package com.ravi.chatserver.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Message {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private String messageType;  // text, image, video, etc.
    private LocalDateTime timestamp;
}
