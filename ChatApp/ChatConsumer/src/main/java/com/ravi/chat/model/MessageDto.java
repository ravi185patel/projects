package com.ravi.chat.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private String messageType;  // text, image, video, etc.
    private LocalDateTime timestamp;
}
