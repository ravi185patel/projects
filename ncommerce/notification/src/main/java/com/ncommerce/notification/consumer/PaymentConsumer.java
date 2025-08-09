package com.ncommerce.notification.consumer;

import com.ncommerce.common.constant.NotificationStatus;
import com.ncommerce.common.dto.PaymentEvent;
import com.ncommerce.notification.entity.NotificationOutBox;
import com.ncommerce.notification.mapper.NotificationMapper;
import com.ncommerce.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "payment-topics")
    public void handlePaymentEvent(PaymentEvent paymentEvent) {
        paymentEvent.setNotificationStatus(NotificationStatus.PENDING);

        NotificationOutBox notificationOutBox =  notificationService.save(NotificationMapper.getNotificationOutBox(paymentEvent));
        try {
            // send email;
            System.out.println("Payment Confirmation email sent for order " + paymentEvent.getEventId());
            paymentEvent.setNotificationStatus(NotificationStatus.SEND);
            paymentEvent.setNotificationId(notificationOutBox.getId());
            notificationService.save(NotificationMapper.getNotificationOutBox(paymentEvent));
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
