package com.dojo.overflow.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dojo.overflow.models.Answer;
import com.dojo.overflow.models.Question;
import com.dojo.overflow.models.Tag;
import com.dojo.overflow.services.AnswerService;
import com.dojo.overflow.services.QuestionService;
import com.dojo.overflow.services.TagService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	private final QuestionService Qservice;
	private final TagService tagService;
	private final AnswerService awService;

	public QuestionController(QuestionService Qservice, TagService tagService, AnswerService awService) { 
		this.Qservice = Qservice;
		this.tagService = tagService;
		this.awService = awService;
	}
	
	@RequestMapping("new")
	public String newForm(@ModelAttribute("question") Question question) {
		return "question/new.jsp";
	}
	
	@RequestMapping(value="new", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("question") Question question, BindingResult formData, @RequestParam(value="tagsList") String tagsList, Model model) {
		if(formData.hasErrors()) {
			return "question/new.jsp";
		}else {
			List<String> newTags = Arrays.asList(tagsList.split("\\s*,\\s*"));
			
			// check the length of the list
			if(tagsList.isEmpty()){
				model.addAttribute("tagsError", "*Tags are required");
				return "question/new.jsp";
			}
			else if(newTags.size() > 3) {
				model.addAttribute("tagsError", "*No more than 3 tags");
				model.addAttribute("tags", tagsList);
				return "question/new.jsp";
			}
			
			// validation pass
			// Create my question
			Question newQ = this.Qservice.createQuestion(question);
			
			// check if tags already exist
			for(String subject: newTags) {
				
				System.out.println("subject: "+ subject);
				Tag tag = this.tagService.findTagBySubject(subject);
				
				if(tag != null) {
					System.out.println("yes tag: "+ tag);
					
					this.Qservice.addTagToQuestion(newQ, tag);
				}
				else {
					System.out.println("No tag: "+ tag);
					
					Tag tempTag = new Tag(subject);
					
					System.out.println("No tagToString: "+ tempTag.toString());
					Tag newTag = this.tagService.createTag(tempTag);
					this.Qservice.addTagToQuestion(newQ, newTag);
				}
			}
			return "redirect:/questions";
		}
	}
	

	@RequestMapping("")
	public String dashboard(Model model) {
		List<Question> questions = this.Qservice.findAllQuestions();
		model.addAttribute("questions", questions);
		return "question/dashboard.jsp";
	}
	
	@RequestMapping("{id}")
	public String show(@PathVariable("id") Long id, @ModelAttribute("answer") Answer answer, Model model) {
		Question question = this.Qservice.findQuestionById(id);
		
		model.addAttribute("question", question);
		return "question/show.jsp";
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.POST )
	public String create(@Valid @ModelAttribute("answer") Answer answer, BindingResult formData, @PathVariable("id") Long id, Model model) {
		Question question = this.Qservice.findQuestionById(id);
		if(formData.hasErrors()) {
			model.addAttribute("question", question);
			return "question/show.jsp";
		}
		else {
			// create a answer
			Answer newAwswer = this.awService.createAnswer(answer);
			
			// add answer to the question
			this.awService.addAnswerToQuestion(answer, question);
		}
		return "redirect:/questions/"+id;
	}
}
