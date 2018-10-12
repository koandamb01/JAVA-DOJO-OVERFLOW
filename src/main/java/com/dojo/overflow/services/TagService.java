package com.dojo.overflow.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dojo.overflow.models.Tag;
import com.dojo.overflow.repositories.TagRepo;

@Service
public class TagService {
	private final TagRepo tagRepo;
	
	public TagService(TagRepo tagRepo) { this.tagRepo = tagRepo; }
	
	// create a tag
	public Tag createTag(Tag tag) { return this.tagRepo.save(tag); }
	
	// get all the tag
	public List<Tag> findAllTags(){ return this.tagRepo.findAll(); }
	
	
	// get tag by subject
	public Tag findTagBySubject(String subject) {
		Tag tag = this.tagRepo.findTagBySubject(subject);
		if(tag != null) {
			return tag;
		}else {
			return null;
		}
	}
}
