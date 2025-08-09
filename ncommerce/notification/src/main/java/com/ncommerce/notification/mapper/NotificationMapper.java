package com.ncommerce.notification.mapper;



import com.ncommerce.common.dto.Notification;
import com.ncommerce.notification.entity.NotificationOutBox;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationMapper {

    public static NotificationOutBox getNotificationOutBox(Notification notification){
        NotificationOutBox notificationOutBox = new NotificationOutBox();
        notificationOutBox.setId(notification.getNotificationId());
        notificationOutBox.setEventId(notification.getEventId());
        notificationOutBox.setEventType(notification.getEventType());
        notificationOutBox.setCreatedAt(LocalDate.now());
        notificationOutBox.setStatus(notification.getNotificationStatus());
        return notificationOutBox;
    }
//    public static com.ncommerce.notification.entity.Notification dtoToEntity(Notification notificationDto){
//        com.ncommerce.notification.entity.Notification notification = new com.ncommerce.notification.entity.Notification();
//        notification.setNotificationStatus(notificationDto.getNotificationStatus());
////        notification.setOrderId(notificationDto.getOrderId());
////        notification.setPaymentId(notificationDto.getPaymentId());
////        notification.setPublishDate(notificationDto.getPublishDate());
//        notification.setNotificationId(notificationDto.getNotificationId());
//        return notification;
//    }
//
//    public static Notification entityToDto(com.ncommerce.notification.entity.Notification notification){
//        Notification notificationDto = new Notification();
//        notificationDto.setNotificationStatus(notification.getNotificationStatus());
////        notificationDto.setOrderId(notification.getOrderId());
////        notificationDto.setPaymentId(notification.getPaymentId());
////        notificationDto.setPublishDate(notification.getPublishDate());
////        notificationDto.setNotificationType(notification.getNotificationType());
//        return notificationDto;
//    }
//
//    public static List<Notification> entityListIntoDtoList(List<com.ncommerce.notification.entity.Notification> notifications){
//        return notifications.stream().map(NotificationMapper::entityToDto).collect(Collectors.toList());
//    }

}
