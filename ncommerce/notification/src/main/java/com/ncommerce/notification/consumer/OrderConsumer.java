package com.ncommerce.notification.consumer;

import com.ncommerce.common.constant.NotificationStatus;
import com.ncommerce.common.dto.OrderEvent;
import com.ncommerce.notification.entity.NotificationOutBox;
import com.ncommerce.notification.mapper.NotificationMapper;
import com.ncommerce.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    private NotificationService notificationService;


    @KafkaListener(topics = "order-topics")
    public void handleOrderEvent(OrderEvent orderEvent) {
        orderEvent.setNotificationStatus(NotificationStatus.PENDING);
        NotificationOutBox notificationOutBox = notificationService.save(NotificationMapper.getNotificationOutBox(orderEvent));
        try {
//                emailSender.send(
//                        event.getCustomerEmail(),
//                        "Order Confirmation #" + event.getOrderId(),
//                        "Thank you for your payment!"
//                );
            System.out.println("Email sent for order " + orderEvent.getEventId()+" status: "+ orderEvent.getStatus());
            orderEvent.setNotificationStatus(NotificationStatus.SEND);
            orderEvent.setNotificationId(notificationOutBox.getId());
            notificationService.save(NotificationMapper.getNotificationOutBox(orderEvent));
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
            // No rollback - payment remains valid
        }
    }
}
