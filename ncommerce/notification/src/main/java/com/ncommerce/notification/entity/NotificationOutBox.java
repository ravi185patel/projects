package com.ncommerce.notification.entity;

import com.ncommerce.common.constant.EventType;
import com.ncommerce.common.constant.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="notification_outbox")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationOutBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long eventId;

    @Column(nullable = false)
    private EventType eventType;

    @Column(nullable = false)
    private NotificationStatus status;

    @Column(nullable = false)
    private LocalDate createdAt;
}
