package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AnnouncementController {

    @Autowired
    private AnnouncementService service;

    @GetMapping("/announcements")
    public List<Announcement> getAll() {
        return service.getAll();
    }

    // ✅ NEW (IMPORTANT)
    @GetMapping("/announcements/ward")
    public List<Announcement> getByWard(@RequestParam int wardNumber) {
        return service.getByWard(wardNumber);
    }

    @PostMapping("/announcements")
    public Announcement create(@RequestBody Announcement a) {
        a.setTimestamp(java.time.LocalDateTime.now()); // ✅ fix
        return service.create(a);
    }

    @PutMapping("/announcements/{id}/like")
    public void like(@PathVariable Long id) {
        service.like(id);
    }
    
    @DeleteMapping("/announcements/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted";
    }
}