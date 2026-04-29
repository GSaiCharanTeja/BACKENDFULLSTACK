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
    private EmailService emailService;
    // ================= OTP STORAGE =================
    private Map<String, String> otpStore = new HashMap<>();

    // ================= SIGNUP =================
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return service.register(user);
    }

    // ================= LOGIN =================
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

    // ================= CREATE USER =================
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    // ================= GET USERS =================
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // ================= DELETE USER =================
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "User deleted successfully ✅";
    }

    // ================= UPDATE USER =================
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return service.updateUser(id, updatedUser);
    }

    // ================= STORAGE COUNT =================
    @GetMapping("/storage")
    public Map<String, Object> getStorage() {
        return Map.of("users", service.count());
    }

    // ================= CLEAR USERS =================
    @DeleteMapping("/users/clear-all")
    public String clearAllUsers() {
        service.clearAllUsers();
        return "All users deleted successfully 💀";
    }

    // =========================================================
    // ✅ SEND OTP
    // =========================================================
  @PostMapping("/send-otp")
public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> body) {

    String email = body.get("email");

    if (email == null || email.isEmpty()) {
        return ResponseEntity.badRequest()
                .body(Map.of("message", "Email is required"));
    }

    String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

    otpStore.put(email, otp);

    boolean isSent = emailService.sendOtp(email, otp);

    if (isSent) {
        return ResponseEntity.ok(Map.of("message", "OTP sent successfully"));
    } else {
        return ResponseEntity.status(500)
                .body(Map.of("message", "Email sending failed ❌"));
    }
}
    // =========================================================
    // ✅ VERIFY OTP
    // =========================================================
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String enteredOtp = body.get("otp");

        String storedOtp = otpStore.get(email);

        if (storedOtp != null && storedOtp.equals(enteredOtp)) {
            otpStore.remove(email);
            return ResponseEntity.ok(Map.of("message", "OTP verified successfully"));
        }

        return ResponseEntity.status(400)
                .body(Map.of("message", "Invalid OTP ❌"));
    }
}
