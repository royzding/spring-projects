package com.spring.data.jpa.pagingsorting.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.data.jpa.pagingsorting.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  Page<Tutorial> findByPublished(boolean published, Pageable pageable);

  Page<Tutorial> findByTitleContaining(String title, Pageable pageable);
  
  Page<Tutorial> findByTitleIn(List<String> titles, Pageable pageable);
  
  //1. Spring JPA In cause using method name
  List<Tutorial> findByTitleIn(List<String> titles); 
  
  //2. Spring JPA In cause using @Query
  @Query("SELECT t FROM Tutorial t WHERE t.title IN (:titles)")
  List<Tutorial> findByTitles1(@Param("titles")List<String> titles);
  
  //3. Spring JPA In cause using native query
  @Query(nativeQuery=true,value = "SELECT * FROM tutorials t WHERE t.title IN (:titles)") 
  List<Tutorial> findByTitles2(@Param("titles") List<String> titles);  
  
  List<Tutorial> findByTitleContaining(String title, Sort sort);  
}
