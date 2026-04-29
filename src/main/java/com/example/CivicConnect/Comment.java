package com.example.CivicConnect;

import java.time.LocalDateTime;


import jakarta.persistence.*;
@Entity
public class Comment {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long announcementId;
    private String userName;
    private String content;
    private LocalDateTime timestamp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
