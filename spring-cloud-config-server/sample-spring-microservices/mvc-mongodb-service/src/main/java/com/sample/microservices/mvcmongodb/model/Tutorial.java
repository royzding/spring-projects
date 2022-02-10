package com.sample.microservices.mvcmongodb.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Document(collection = "tutorials")
@Data
public class Tutorial {
  @Id
  private String id;

  @JsonProperty("TUTORIAL_TITLE")
  private String title;
  private String description;
  private boolean published;
 
  public Tutorial() {

  }

  public Tutorial(String title, String description, boolean published) {
    this.title = title;
    this.description = description;
    this.published = published;
  }
  
  @JsonProperty("CURRENT_TIME")
  public String getCurrentTime() {
	  return LocalDateTime.now().toString();
  }

}
