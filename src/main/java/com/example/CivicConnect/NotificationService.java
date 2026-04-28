package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repo;

    public void createNotification(String msg, int wardNumber) {
        Notification n = new Notification();
        n.setMessage(msg);
        n.setWardNumber(wardNumber);
        n.setRead(false);
        n.setTimestamp(LocalDateTime.now());
        repo.save(n);
    }
    
    public List<Notification> getByWardNumber(int wardNumber) {
        return repo.findByWardNumberOrderByTimestampDesc(wardNumber);
    }
    
}