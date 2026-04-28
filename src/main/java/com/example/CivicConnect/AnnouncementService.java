package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository repo;

    @Autowired
    private NotificationService notificationService; // ✅ correct service

    public List<Announcement> getAll() {
        return repo.findAll();
    }

    public List<Announcement> getByWard(int wardNumber) {
        return repo.findByWardNumber(wardNumber);
    }

    // ✅ SINGLE create method (fixed)
    public Announcement create(Announcement a) {
        a.setTimestamp(LocalDateTime.now());
        a.setLikes(0);

        Announcement saved = repo.save(a);

        // 🔔 Trigger notification
        notificationService.createNotification(
            "New announcement: " + a.getTitle(),
            a.getWardNumber()
        );

        return saved;
    }

    public void like(Long id) {
        Announcement a = repo.findById(id).orElseThrow();
        a.setLikes(a.getLikes() + 1);
        repo.save(a);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public long totalAnnouncements() {
        return repo.count();
    }

    public long totalByWard(int wardNumber) {
        return repo.countByWardNumber(wardNumber);
    }

    public long totalLikes() {
        return repo.findAll()
                   .stream()
                   .mapToLong(Announcement::getLikes)
                   .sum();
    }
}