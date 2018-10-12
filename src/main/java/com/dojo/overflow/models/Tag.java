package com.dojo.overflow.models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tags")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="*Tags are required")
	private String subject;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	 
	@PrePersist
	protected void onCreate(){ this.createdAt = new Date(); }
	@PreUpdate
	protected void onUpdate() { this.updatedAt = new Date(); }
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="questions_tags",
		joinColumns = @JoinColumn(name="tag_id"),
		inverseJoinColumns = @JoinColumn(name="question_id")
	)
	private List<Question> questions = new ArrayList<>();

	public Tag() {}
	public Tag(String subject) {this.subject = subject;}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}	
}
