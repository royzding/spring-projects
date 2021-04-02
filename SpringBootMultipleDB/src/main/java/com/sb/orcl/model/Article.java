package com.sb.orcl.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Article")
@Table(name = "Article")
public class Article {
 
    @Id  
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARTICLE_SEQ")
    @SequenceGenerator(sequenceName = "ARTICLE_SEQ", allocationSize = 1, name = "ARTICLE_SEQ")
    private Long id;
 
    private String title;
    
    /*

 	//Unidirectional @OneToMany with @JoinColumn
 	//Review class will not have private Article article;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "post_id")
	private List<PostComment> comments = new ArrayList<>();     
     
    */
 
    //Bidirectional @OneToMany
    //Review class will need to have private Article article
    
    @JsonManagedReference  //break up infinite loop
    @OneToMany(
        mappedBy = "article",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Review> reviews;    
 
    public Article() {
		super();
	}

	public Article(String title) {
		super();
		this.title = title;
	}

	public Article(String title, List<Review> reviews) {
		super();
		this.title = title;
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReview(Review review) {
        reviews.add(review);
        review.setArticle(this);
    }
 
    public void removeReview(Review review) {
        reviews.remove(review);
        review.setArticle(null);
    }
}