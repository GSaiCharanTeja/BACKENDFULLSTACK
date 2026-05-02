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

    // ================= SIGNUP (OLD - OPTIONAL KEEP) =================
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

    // ================= CREATE USER (ADMIN) =================
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {

        // 🔥 prevent duplicate email
        if (service.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists ❌");
        }

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

    // ✅ CHECK EMAIL
@GetMapping("/check-email")
public ResponseEntity<?> checkEmail(@RequestParam String email) {
    email = email.toLowerCase().trim();
    boolean exists = service.existsByEmail(email);
    return ResponseEntity.ok(Map.of("exists", exists));
}

// ✅ SEND OTP
@PostMapping("/send-otp")
public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> body) {

    String email = body.get("email");

    // ✅ Null + empty check
    if (email == null || email.trim().isEmpty()) {
        return ResponseEntity.badRequest()
                .body(Map.of("message", "Email is required"));
    }

    email = email.toLowerCase().trim();

    // ✅ Email already exists
    if (service.existsByEmail(email)) {
        return ResponseEntity.status(409)
                .body(Map.of("message", "Email already registered ❌"));
    }

    // ✅ Generate OTP
    String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

    otpStore.put(email, otp);
    otpExpiry.put(email, System.currentTimeMillis() + 5 * 60 * 1000);

    boolean isSent = emailService.sendOtp(email, otp);

    if (isSent) {
        return ResponseEntity.ok(Map.of("message", "OTP sent successfully"));
    }

    return ResponseEntity.status(500)
            .body(Map.of("message", "Email sending failed ❌"));
}
    private Map<String, Long> otpExpiry = new HashMap<>();

    @PostMapping("/verify-otp")
public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> body) {

    String email = body.get("email");
    String otp = body.get("otp");

    if (email == null || otp == null) {
        return ResponseEntity.badRequest()
                .body(Map.of("message", "Email and OTP required ❌"));
    }

    email = email.toLowerCase().trim();

    String storedOtp = otpStore.get(email);
    Long expiry = otpExpiry.get(email);

    // ❌ No OTP found
    if (storedOtp == null || expiry == null) {
        return ResponseEntity.status(400)
                .body(Map.of("message", "OTP not found ❌"));
    }

    // ❌ Expired
    if (System.currentTimeMillis() > expiry) {
        otpStore.remove(email);
        otpExpiry.remove(email);
        return ResponseEntity.status(400)
                .body(Map.of("message", "OTP expired ❌"));
    }

    // ❌ Incorrect
    if (!storedOtp.equals(otp)) {
        return ResponseEntity.status(400)
                .body(Map.of("message", "Invalid OTP ❌"));
    }

    // ✅ OTP correct → create user
    User user = new User();
    user.setEmail(email);
    user.setName(body.get("name"));
    user.setPassword(body.get("password"));
    user.setRole("CITIZEN"); // or from body if needed

    // save user (uses hashing)
    service.createUser(user);

    // cleanup
    otpStore.remove(email);
    otpExpiry.remove(email);

    return ResponseEntity.ok(user);
}
}
