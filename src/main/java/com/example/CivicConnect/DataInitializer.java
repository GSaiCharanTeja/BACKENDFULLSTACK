package com.example.CivicConnect;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(UserRepository repo) {
        return args -> {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (!repo.existsByEmail("admin@civic.gov")) {

                User u = new User();
                u.setName("Admin User");
                u.setEmail("admin@civic.gov");

                // 🔥 ENCRYPT PASSWORD
                u.setPassword(encoder.encode("password"));

                u.setRole("ADMIN");
                u.setWardNumber(0);

                repo.save(u);

                System.out.println("✅ Default admin created");
            }
        };
    }
}