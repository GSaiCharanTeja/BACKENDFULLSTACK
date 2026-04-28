package com.example.CivicConnect;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
@Entity
public class Response {
	@Id
	private String id;
	 private String issueId;
	    private String content;
	    private String authorName;
	    private String authorRole;
	    private String status;
	    private LocalDateTime RespondedAt;
	    
		public LocalDateTime getRespondedAt() {
			return RespondedAt;
		}
		public void setRespondedAt(LocalDateTime respondedAt) {
			RespondedAt = respondedAt;
		}
		public String getIssueId() {
			return issueId;
		}
		public void setIssueId(String issueId) {
			this.issueId = issueId;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getAuthorName() {
			return authorName;
		}
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}
		public String getAuthorRole() {
			return authorRole;
		}
		public void setAuthorRole(String authorRole) {
			this.authorRole = authorRole;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	    
}	
