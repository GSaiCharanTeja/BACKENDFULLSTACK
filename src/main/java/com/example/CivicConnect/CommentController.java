package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CommentController {

    @Autowired
    private CommentService service;

    // 💬 Add comment
    @PostMapping("/comments")
    public Comment add(@RequestBody Comment c) {
        return service.addComment(c);
    }

    // 💬 Get comments for announcement
    @GetMapping("/comments/{announcementId}")
    public List<Comment> get(@PathVariable Long announcementId) {
        return service.getComments(announcementId);
    }
}