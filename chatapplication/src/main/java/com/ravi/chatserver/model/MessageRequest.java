package com.ravi.chatserver.model;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    @NotBlank
    private String senderId;

    @NotBlank
    private String receiverId;

    @NotBlank
    private String content;

    private String messageType; // optional: default to "TEXT"

    // Getters and Setters
}
