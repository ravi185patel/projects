package com.ncommerce.notification.entity;


import com.ncommerce.common.constant.NotificationStatus;
import com.ncommerce.common.constant.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long paymentId;

    @Column(nullable = false)
    private LocalDate publishDate;

    @Column(nullable = false)
    private NotificationStatus notificationStatus;

    @Column(nullable = false)
    private PaymentStatus notificationType;
}