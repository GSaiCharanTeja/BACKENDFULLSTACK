package com.example.CivicConnect;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, String> {

    List<Issue> findByCitizenId(Long CitizenId);
    List<Issue> findByWardNumberAndReviewStatus(int wardNumber,String reviewStatus);
    List<Issue> findByReviewStatus(String reviewStatus);
}