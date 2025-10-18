package com.ravi.chat.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String senderId;
    private String receiverId;
    private String content;
    private String messageType;  // text, image, video, etc.
    private LocalDateTime timestamp;
}
