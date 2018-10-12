package com.dojo.overflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dojo.overflow.models.Answer;
import com.dojo.overflow.models.Question;
import com.dojo.overflow.models.Tag;
import com.dojo.overflow.repositories.QuestionRepo;

@Service
public class QuestionService {
	private final QuestionRepo Qrepo;
	
	public QuestionService(QuestionRepo Qrepo) { this.Qrepo = Qrepo; }
	
	// create a new question
	public Question createQuestion(Question Q) { return this.Qrepo.save(Q); }
	
	// get all the questions
	public List<Question> findAllQuestions(){ return this.Qrepo.findAll(); }
	
	// get a question by id
	public Question findQuestionById(Long id) {
		Optional<Question> res = this.Qrepo.findById(id);
		if(res.isPresent()) {
			return res.get();
		}else {
			return null;
		}
	}
	
	// add tag to Questions
	public void addTagToQuestion(Question question, Tag tag) {
		question.getTags().add(tag);
		this.Qrepo.save(question);
	}
	
}
