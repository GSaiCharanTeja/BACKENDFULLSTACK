package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AnalyticsController {

    @Autowired
    private AnnouncementService service;

    @GetMapping("/analytics")
    public Map<String, Object> getStats(@RequestParam int wardNumber) {

        Map<String, Object> stats = new HashMap<>();

        stats.put("totalAnnouncements", service.totalAnnouncements());
        stats.put("wardAnnouncements", service.totalByWard(wardNumber));
        stats.put("totalLikes", service.totalLikes());

        return stats;
    }
}