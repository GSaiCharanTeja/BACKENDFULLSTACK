package com.example.CivicConnect;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @PostMapping("/reset")
    public ResponseEntity<?> resetUsers() {

        // Example logic (you can change this)
        System.out.println("Reset API called");

        return ResponseEntity.ok("Users reset successfully");
    }
}