package com.bezkoder.spring.hibernate.manytomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.hibernate.manytomany.exception.ResourceNotFoundException;
import com.bezkoder.spring.hibernate.manytomany.model.Tag;
import com.bezkoder.spring.hibernate.manytomany.model.Tutorial;
import com.bezkoder.spring.hibernate.manytomany.repository.TagRepository;
import com.bezkoder.spring.hibernate.manytomany.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TagController {

	@Autowired
	private TutorialRepository tutorialRepository;

	@Autowired
	private TagRepository tagRepository;

	// ********************************
	// POST (Create) methods
	// ********************************
	@PostMapping("/tags/create")
	@Transactional
	public ResponseEntity<Tag> createTag(@RequestBody Tag tagRequest) {
		
		Tag tag = tagRepository.save(tagRequest);
		return new ResponseEntity<>(tag, HttpStatus.CREATED);
	}
	
	
	// ********************************
	// GET methods
	// ********************************
	@GetMapping("/tags")
	public ResponseEntity<List<Tag>> getAllTags() {
		
		List<Tag> tags = tagRepository.findAll();		

		if (tags.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(tags, HttpStatus.OK);
	}

	@GetMapping("/tutorials/{tutorialId}/tags")
	public ResponseEntity<List<Tag>> getAllTagsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
		
		if (!tutorialRepository.existsById(tutorialId)) {
			throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
		}

		List<Tag> tags = tagRepository.findTagsByTutorialsId(tutorialId);
		return new ResponseEntity<>(tags, HttpStatus.OK);
	}

	@GetMapping("/tags/{id}")
	public ResponseEntity<Tag> getTagsById(@PathVariable(value = "id") Long id) {
		
		Tag tag = tagRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));

		return new ResponseEntity<>(tag, HttpStatus.OK);
	}

	@GetMapping("/tags/{tagId}/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorialsByTagId(@PathVariable(value = "tagId") Long tagId) {
		
		if (!tagRepository.existsById(tagId)) {
			throw new ResourceNotFoundException("Not found Tag  with id = " + tagId);
		}

		List<Tutorial> tutorials = tutorialRepository.findTutorialsByTagsId(tagId);
		return new ResponseEntity<>(tutorials, HttpStatus.OK);
	}

	// ********************************
	// UPDATE methods
	// ********************************
	@PutMapping("/tags/{id}")
	public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tagRequest) {
		
		Tag tag = tagRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));

		tag.setName(tagRequest.getName());

		return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.OK);
	}

	@PutMapping("/tags/{tutorialId}/{tagId}")
	@Transactional
	public ResponseEntity<?> addTag(@PathVariable("tutorialId") Long tutorialId, @PathVariable("tagId") Long tagId) {
		
		Tutorial tutorial= tutorialRepository.findById(tutorialId).get();
		Tag _tag = tagRepository.findById(tagId).get();
		
		tutorial.addTag(_tag);
		
//		tagRepository.save(_tag);
		
		Tutorial returnedValue = tutorialRepository.save(tutorial);

		return new ResponseEntity<>(returnedValue, HttpStatus.CREATED);
	}
	
	// ********************************
	// DELETE methods
	// ********************************
	@DeleteMapping("/tutorials/{tutorialId}/tags/{tagId}")
	@Transactional
	public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "tutorialId") Long tutorialId, @PathVariable(value = "tagId") Long tagId) {
		
		Tutorial tutorial = tutorialRepository.findById(tutorialId)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

		Tag _tag = tagRepository.findById(tagId).get();
		
		tutorial.removeTag(_tag);
		tutorialRepository.save(tutorial);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/tags/{id}")
	@Transactional
	public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long tagId) {

		List<Tutorial> _tutorials = tutorialRepository.findAll();
		
		Tag _tag = tagRepository.findById(tagId).get();
		
		for (Tutorial tutorial : _tutorials) {
			
			boolean contains = tutorial.getTags().contains(_tag);
			
			if(contains) {
				tutorial.removeTag(_tag);
				tutorialRepository.save(tutorial);				
			}			
		}		
		
		tagRepository.deleteById(tagId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
