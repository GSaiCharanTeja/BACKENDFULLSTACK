package com.example.CivicConnect;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Issue {

    @Id
    private String id;

    private String title;
    private String category;
    private String description;

    private String urgency;   // 🔥 NEW
    private String location;  // 🔥 NEW

    private Long citizenId;
    private String citizenName;
    private String constituency;

    private String status;
    private int votes;
    private int wardNumber;
    
    private String reviewStatus;
    private LocalDateTime createdAt;
    public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public int getWardNumber() {
		return wardNumber;
	}
	public void setWardNumber(int wardNumber) {
		this.wardNumber = wardNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(Long citizenId) {
		this.citizenId = citizenId;
	}
	public String getCitizenName() {
		return citizenName;
	}
	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public boolean isFlagged() {
		return flagged;
	}
	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}
	private boolean flagged;
	@Override
	public String toString() {
		return "Issue [id=" + id + ", title=" + title + ", category=" + category + ", description=" + description
				+ ", urgency=" + urgency + ", location=" + location + ", citizenId=" + citizenId + ", citizenName="
				+ citizenName + ", constituency=" + constituency + ", status=" + status + ", votes=" + votes
				+ ", flagged=" + flagged + "]";
	}

    // Getters & Setters
}
