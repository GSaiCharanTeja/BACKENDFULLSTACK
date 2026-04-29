package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ✅ SIGNUP
    public String register(User user) {

        if (repo.existsByEmail(user.getEmail())) {
            return "User already exists ❌";
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("CITIZEN");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);

        return "User registered successfully ✅";
    }

    // ✅ LOGIN
    public User login(User user) {

        Optional<User> existing = repo.findByEmail(user.getEmail());

        if (existing.isEmpty()) {
            return null;
        }

        User dbUser = existing.get();

        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return null;
        }

        return dbUser;
    }

    // ✅ CREATE USER (ADMIN)
    public User createUser(User user) {

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("POLITICIAN");
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

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setRole(updatedUser.getRole());
        user.setStreet(updatedUser.getStreet());
        user.setDistrict(updatedUser.getDistrict());
        user.setState(updatedUser.getState());
        user.setWardNumber(updatedUser.getWardNumber());

        return repo.save(user);
    }
    public void clearAllUsers() {
        repo.deleteAll();
    }

    public long count() {
    return repo.count();
}
}
