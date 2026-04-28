package com.example.CivicConnect;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface AnnouncementRepository extends JpaRepository<Announcement, Long>{
		List<Announcement> findByWardNumber(int wardNumber);
		long countByWardNumber(int wardNumber);
}
