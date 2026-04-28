package com.example.CivicConnect;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/flags")
@CrossOrigin(origins = "http://localhost:5173")
public class FlagController {

    private final FlagService flagService;

    public FlagController(FlagService flagService) {
        this.flagService = flagService;
    }

    @PostMapping
    public ResponseEntity<?> addFlag(@RequestBody Flag flag) {
        System.out.println(flag);
        return ResponseEntity.ok(flag);
    }

    @GetMapping   // ✅ FIXED
    public List<Flag> getAllFlags() {
        return flagService.getAllFlags();
    }
}