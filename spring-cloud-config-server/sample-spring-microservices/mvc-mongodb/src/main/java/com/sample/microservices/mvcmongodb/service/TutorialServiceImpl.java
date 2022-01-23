package com.sample.microservices.mvcmongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sample.microservices.mvcmongodb.model.Tutorial;
import com.sample.microservices.mvcmongodb.repository.TutorialRepository;

@Service
public class TutorialServiceImpl implements TutorialService {

	  private final TutorialRepository tutorialRepository;
	  
	  TutorialServiceImpl(TutorialRepository tutorialRepository) {
		  this.tutorialRepository = tutorialRepository;		  
	  }

	  @Override
	  public List<Tutorial> getAllTutorials() {
	       return tutorialRepository.findAll();
	  }

	  @Override
	  public List<Tutorial> getTutorialsByName(final String title) {
		  
	      return tutorialRepository.findByTitleContaining(title);
	  }

	  @Override
	  public Tutorial getTutorialById(final String id) {
		  Optional<Tutorial> tut = tutorialRepository.findById(id);
	      return !tut.isEmpty() ? tut.get() : null;
	  }

	  @Override
	  public Tutorial createTutorial(final Tutorial tutorial) {

	      return tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.isPublished()));
	  }

	  @Override
	  public Tutorial updateTutorial(final String id, final Tutorial tutorial) {
		  Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

	      Tutorial _tutorial = tutorialData.get();
	      _tutorial.setTitle(tutorial.getTitle());
	      _tutorial.setDescription(tutorial.getDescription());
	      _tutorial.setPublished(tutorial.isPublished());
	      return tutorialRepository.save(_tutorial);
	  }

	  @Override
	  public void deleteTutorial(final String id) {
	      tutorialRepository.deleteById(id);
	  }

	  @Override
	  public void deleteAllTutorials() {		  
		  tutorialRepository.deleteAll();
	  }

	  @Override
	  public List<Tutorial> findByPublished() {
	    return tutorialRepository.findByPublished(true);
	  }

}
