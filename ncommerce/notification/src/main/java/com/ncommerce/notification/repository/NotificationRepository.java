package com.ncommerce.notification.repository;

import com.ncommerce.notification.entity.Notification;
import com.ncommerce.notification.entity.NotificationOutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationOutBox,Long> {

//    List<Notification> findAllByNotificationId(Long notificationId);
}
