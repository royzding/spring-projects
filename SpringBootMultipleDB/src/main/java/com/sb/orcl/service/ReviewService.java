package com.sb.orcl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.orcl.model.Article;
import com.sb.orcl.model.Review;
import com.sb.orcl.repo.ArticleRepo;
import com.sb.orcl.repo.ReviewRepo;

@Service
public class ReviewService {
	
	@Autowired
	ArticleRepo articleRepo;
	
	@Autowired
	ReviewRepo reviewRepo;
	
	public List<Review> findAll() {
		
		return reviewRepo.findAll();
	}
	
	public Review findById(long id) {
		return reviewRepo.findById(id).get();
	}

	public Review addReview(Review review) {
        Article article = articleRepo.findById(review.getArticle().getId()).orElse(null);
        if (null == article) {
            article = new Article();
        }
        article.setId(review.getArticle().getId());
        review.setArticle(article);
        
		return reviewRepo.save(review);
	}
	
	public Review save(Review review) {
		return reviewRepo.save(review);
	}
	
	public void delete(Review review) {
		reviewRepo.delete(review);
	}
	
	public Review update(Review review) {
		return reviewRepo.save(review);
	}

}
