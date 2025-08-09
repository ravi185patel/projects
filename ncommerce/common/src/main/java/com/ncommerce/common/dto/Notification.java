package com.ncommerce.common.dto;

import com.ncommerce.common.constant.EventType;
import com.ncommerce.common.constant.NotificationStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    private Long notificationId;
    private Long eventId;
    private LocalDate sendOn;
    private NotificationStatus notificationStatus;
    private EventType eventType;
}
