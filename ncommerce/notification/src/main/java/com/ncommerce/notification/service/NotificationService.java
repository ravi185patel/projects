package com.ncommerce.notification.service;

import com.ncommerce.common.dto.Notification;
import com.ncommerce.notification.dao.NotificationDao;
import com.ncommerce.notification.entity.NotificationOutBox;
import com.ncommerce.notification.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationDao notificationDao;

//    public List<Notification> allNotification(Long notificationId){
//        return NotificationMapper.entityListIntoDtoList(notificationDao.allNotification(notificationId));
//    }

    public NotificationOutBox save(NotificationOutBox notificationOutBox){
        return notificationDao.save(notificationOutBox);
    }



}
