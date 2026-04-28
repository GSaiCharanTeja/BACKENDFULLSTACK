package com.example.CivicConnect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repo; // ✅ correct type

    public Comment addComment(Comment c) {
        c.setTimestamp(LocalDateTime.now());
        return repo.save(c);
    }

    public List<Comment> getComments(Long announcementId) {
        return repo.findByAnnouncementId(announcementId);
    }
}