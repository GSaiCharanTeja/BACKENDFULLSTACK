package com.example.CivicConnect;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
@Service
public class IssueService {

    @Autowired
    private IssueRepository repo;

    
    public List<Issue> getIssuesByUser(Long userId) {
        return repo.findByCitizenId(userId);
    }

    public void deleteIssue(String id) {
        repo.deleteById(id);
    }

    public List<Issue> getAllIssues() {
        return repo.findAll();
    }

    public List<Issue> getIssuesByWard(int wardNumber) {
        return repo.findByWardNumberAndReviewStatus(wardNumber, "APPROVED");
    }

    public Issue updateStatus(String id, String status) {
        Issue issue = repo.findById(id).orElseThrow();
        issue.setStatus(status);
        return repo.save(issue);
    }

    public List<Issue> getPendingIssues() {
        return repo.findByReviewStatus("PENDING");
    }

    public Issue approveIssue(String id) {
        Issue issue = repo.findById(id).orElseThrow();
        issue.setReviewStatus("APPROVED");
        return repo.save(issue);
    }

    public Issue rejectIssue(String id) {
        Issue issue = repo.findById(id).orElseThrow();
        issue.setReviewStatus("REJECTED");
        return repo.save(issue);
    }
    
    public Issue createIssue(Issue issue) {
        issue.setId(UUID.randomUUID().toString());
        issue.setReviewStatus("PENDING");
        issue.setCreatedAt(LocalDateTime.now());
        return repo.save(issue);
    }
}