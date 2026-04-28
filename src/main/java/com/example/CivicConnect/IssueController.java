package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class IssueController {

    @Autowired
    private IssueService service;
    @GetMapping("/issues")
    public List<Issue> getAllIssues() {
        return service.getAllIssues();
    }
    
    @PostMapping("/issues")
    public Issue createIssue(@RequestBody Issue issue) {
        return service.createIssue(issue);
    }

    @GetMapping("/issues/user")
    public List<Issue> getIssuesByUser(@RequestParam Long userId) {
        return service.getIssuesByUser(userId);
    }

    @GetMapping("/issues/ward")
    public List<Issue> getIssuesByWard(@RequestParam int wardNumber) {
        return service.getIssuesByWard(wardNumber);
    }

    @PutMapping("/issues/{id}/status")
    public Issue updateStatus(@PathVariable String id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/issues/pending")
    public List<Issue> getPendingIssues() {
        return service.getPendingIssues();
    }

    @PutMapping("/issues/{id}/approve")
    public Issue approveIssue(@PathVariable String id) {
        return service.approveIssue(id);
    }

    @PutMapping("/issues/{id}/reject")
    public Issue rejectIssue(@PathVariable String id) {
        return service.rejectIssue(id);
    }

    @DeleteMapping("/issues/{id}")
    public String deleteIssue(@PathVariable String id) {
        service.deleteIssue(id);
        return "Deleted";
    }
    
    
}