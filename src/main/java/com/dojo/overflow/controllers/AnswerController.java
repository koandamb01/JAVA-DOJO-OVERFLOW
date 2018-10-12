package com.dojo.overflow.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dojo.overflow.services.AnswerService;

@Controller
@RequestMapping("/answers")
public class AnswerController {
	private final AnswerService awService;
	
	public AnswerController(AnswerService awService) { this.awService = awService; }
	
}
