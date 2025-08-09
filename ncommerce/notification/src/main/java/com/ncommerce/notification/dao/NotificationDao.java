package com.ncommerce.notification.dao;

import com.ncommerce.notification.entity.Notification;
import com.ncommerce.notification.entity.NotificationOutBox;
import com.ncommerce.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationDao {

    @Autowired
    private NotificationRepository notificationRepository;

//    public List<Notification> allNotification(Long notificationId){
//        return notificationRepository.findAllByNotificationId(notificationId);
//    }

    public NotificationOutBox save(NotificationOutBox notificationOutBox){
        return notificationRepository.save(notificationOutBox);
    }
}
