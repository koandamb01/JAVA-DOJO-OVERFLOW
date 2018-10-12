package com.dojo.overflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dojo.overflow.models.Answer;
import com.dojo.overflow.models.Question;
import com.dojo.overflow.repositories.AnswerRepo;

@Service
public class AnswerService {
	private final AnswerRepo awRepo;
	
	public AnswerService(AnswerRepo awRepo) { this.awRepo = awRepo; }
	
	// create an answer
	public Answer createAnswer(Answer aw) { return this.awRepo.save(aw);}
	
	// get all the answers
	public List<Answer> findAllAnswers(){ return this.awRepo.findAll(); }
	
	// add answer to question
	public void addAnswerToQuestion(Answer answer, Question question) {
		answer.setQuestion(question);
		this.awRepo.save(answer);
	}
}
