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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@NotEmpty(message="*Question is required")
	private String content;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate(){ this.createdAt = new Date(); }
	@PreUpdate
	protected void onUpdate() { this.updatedAt = new Date(); }
	
	@OneToMany(mappedBy="question", fetch=FetchType.LAZY)
	private List<Answer> answers = new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="questions_tags",
		joinColumns = @JoinColumn(name="question_id"),
		inverseJoinColumns = @JoinColumn(name="tag_id")
	)
	private List<Tag> tags = new ArrayList<>();
	
	public Question() {}
	public Question(String content) { this.content = content; }
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String question) {
		this.content = question;
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
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}	
}
