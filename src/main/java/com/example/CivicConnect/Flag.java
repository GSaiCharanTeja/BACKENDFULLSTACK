package com.example.CivicConnect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Flag {
		@Id
	 	private Long issueId;
	    private String reason;
	    private String notes;
		public Long getIssueId() {
			return issueId;
		}
		public void setIssueId(Long issueId) {
			this.issueId = issueId;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
}
