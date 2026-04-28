package com.example.CivicConnect;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByWardNumberOrderByTimestampDesc(int wardNumber);
}
