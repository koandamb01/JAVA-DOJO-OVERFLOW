package com.dojo.overflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.overflow.models.Tag;

@Repository
public interface TagRepo extends CrudRepository<Tag,Long>{ 
	List<Tag> findAll();
	
	Tag findTagBySubject(String subject);
}
