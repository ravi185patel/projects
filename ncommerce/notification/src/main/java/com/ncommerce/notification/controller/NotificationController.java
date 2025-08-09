package com.ncommerce.notification.controller;

import com.ncommerce.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    public NotificationService notificationService;


//    @GetMapping("/order/{notificationId}")
//    public ResponseEntity<List<Notification>> allNotification(@PathVariable("notificationId") Long notificationId){
//        return ResponseEntity.ok(notificationService.allNotification(notificationId));
//    }
//
//    @GetMapping("/order")
//    public ResponseEntity<Notification> saveNotification(@RequestBody Notification notificationDto){
//        return ResponseEntity.ok(notificationService.save(notificationDto));
//    }
}
