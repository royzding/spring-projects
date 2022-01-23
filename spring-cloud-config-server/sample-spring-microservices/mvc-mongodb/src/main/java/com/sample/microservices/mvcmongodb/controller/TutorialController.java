package com.sample.microservices.mvcmongodb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.mvcmongodb.model.Tutorial;
import com.sample.microservices.mvcmongodb.service.TutorialService;

@RestController
@RequestMapping("/tutorials")
public class TutorialController {

  private final TutorialService tutorialService;
  
  TutorialController(TutorialService tutorialService) {
	  this.tutorialService = tutorialService;
  }

  @GetMapping
  public List<Tutorial> getAllTutorials() {
	  return this.tutorialService.getAllTutorials();
  }

  @GetMapping("/{id}")
  public Tutorial getTutorialById(@PathVariable("id") String id) {
	  return this.tutorialService.getTutorialById(id);
  }

  @PostMapping
  public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
	  return this.tutorialService.createTutorial(tutorial);
  }

  @PutMapping("/{id}")
  public Tutorial updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
	  return this.tutorialService.updateTutorial(id, tutorial);
  }

  @DeleteMapping("/{id}")
  public void deleteTutorial(@PathVariable("id") String id) {
      this.tutorialService.deleteTutorial(id);
  }

  @DeleteMapping
  public void deleteAllTutorials() {
	  this.tutorialService.deleteAllTutorials();
  }

  @GetMapping("/published")
  public List<Tutorial> findByPublished() {
	  return this.tutorialService.findByPublished();
  }

}
