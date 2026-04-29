package com.example.CivicConnect;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") 
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private EmailService emailService;   // ✅ Added for OTP

    // ✅ SIGNUP
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return service.register(user);
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User loggedInUser = service.login(user);

        if (loggedInUser == null) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("message", "Invalid credentials"));
        }

        return ResponseEntity.ok(loggedInUser);
    }

    // ✅ CREATE USER
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    // ✅ GET ALL USERS
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // ✅ DELETE USER
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "User deleted successfully ✅";
    }

    // ✅ UPDATE USER
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return service.updateUser(id, updatedUser);
    }

    @GetMapping("/storage")
    public Map<String, Object> getStorage() {
        long count = service.count();
        return Map.of("users", count);
    }

    @DeleteMapping("/users/clear-all")
    public String clearAllUsers() {
        service.clearAllUsers();
        return "All users deleted successfully 💀";
    }

    // =========================================================
    // ✅ NEW: SEND OTP API
    // =========================================================
 // ================= VERIFY OTP ================
private Map<String, String> otpStore = new HashMap<>();

    // ================= VERIFY OTP =================
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String enteredOtp = body.get("otp");

        String storedOtp = otpStore.get(email);

        if (storedOtp != null && storedOtp.equals(enteredOtp)) {
            otpStore.remove(email); // optional cleanup
            return ResponseEntity.ok(Map.of("message", "OTP verified successfully"));
        }

        return ResponseEntity.status(400).body(Map.of("message", "Invalid OTP ❌"));
    }
}
