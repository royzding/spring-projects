package sb.test.swagger.simple.model;

import lombok.Data;
import lombok.Getter;
import lombok.AccessLevel;

@Data
public class Book   {

  private Long id;
  private String title;
  private String author;
  
  @Getter(AccessLevel.NONE)
  private Boolean active;
  
  public Boolean isActive() {
	  return this.active;
  }
  

}

