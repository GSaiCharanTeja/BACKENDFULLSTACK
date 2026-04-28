package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/ward")
    public List<Notification> getNotifications(@RequestParam int wardNumber) {
        return notificationService.getByWardNumber(wardNumber);
    }
}