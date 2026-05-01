package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // ✅ CHECK EMAIL
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email.toLowerCase().trim());
    }

    // ✅ SIGNUP
    public String register(User user) {

        String email = user.getEmail().toLowerCase().trim();

        if (repo.existsByEmail(email)) {
            return "Email already exists ❌";
        }

        user.setEmail(email);

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("CITIZEN");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return "Password required ❌";
        }

        user.setPassword(encoder.encode(user.getPassword()));

        try {
            repo.save(user);
        } catch (DataIntegrityViolationException e) {
            return "Email already exists ❌";
        }

        return "User registered successfully ✅";
    }

    // ✅ LOGIN
    public User login(User user) {

        String email = user.getEmail().toLowerCase().trim();

        Optional<User> existing = repo.findByEmail(email);

        if (existing.isEmpty()) return null;

        User dbUser = existing.get();

        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return null;
        }

        dbUser.setPassword(null); // 🔥 hide password
        return dbUser;
    }

    // ✅ CREATE USER (ADMIN)
    public User createUser(User user) {

        String email = user.getEmail().toLowerCase().trim();

        if (repo.existsByEmail(email)) {
            throw new RuntimeException("Email already exists ❌");
        }

        user.setEmail(email);

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("CITIZEN");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("Password required ❌");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return repo.save(user);
    }

    // ✅ GET ALL USERS
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // ✅ DELETE USER
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    // ✅ UPDATE USER
    public User updateUser(Long id, User updatedUser) {

    User user = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (updatedUser.getName() != null && !updatedUser.getName().isEmpty()) {
        user.setName(updatedUser.getName());
    }

    if (updatedUser.getRole() != null && !updatedUser.getRole().isEmpty()) {
        user.setRole(updatedUser.getRole());
    }

    if (updatedUser.getStreet() != null) user.setStreet(updatedUser.getStreet());
    if (updatedUser.getDistrict() != null) user.setDistrict(updatedUser.getDistrict());
    if (updatedUser.getState() != null) user.setState(updatedUser.getState());
    if (updatedUser.getWardNumber() != null) user.setWardNumber(updatedUser.getWardNumber());

    // 🔥 optional password update
    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
        user.setPassword(encoder.encode(updatedUser.getPassword()));
    }

    User saved = repo.save(user);
    saved.setPassword(null); // 🔥 hide password

    return saved;
}
}
