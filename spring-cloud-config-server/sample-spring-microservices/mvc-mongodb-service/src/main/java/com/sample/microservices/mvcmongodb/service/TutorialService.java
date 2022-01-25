package com.sample.microservices.mvcmongodb.service;

import java.util.List;
import java.util.Optional;

import com.sample.microservices.mvcmongodb.model.Tutorial;

public interface TutorialService {

	List<Tutorial> getAllTutorials();
	List<Tutorial> getTutorialsByName(final String title);
	Tutorial getTutorialById(final String id);
	Tutorial createTutorial(final Tutorial tutorial);
	Tutorial updateTutorial(final String id, final Tutorial tutorial);
	void deleteTutorial(final String id);
	void deleteAllTutorials();
	List<Tutorial> findByPublished();
	
}
