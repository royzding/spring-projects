package com.sb.orcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.orcl.model.Review;
import com.sb.orcl.service.ReviewService;

@RestController  
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/review")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/list") 
	public List<Review> list() 
	{
		return reviewService.findAll();
	}
	
	@GetMapping("/{id}")
	public Review get(@PathVariable("id") int id) {
		return reviewService.findById(id);
	}
	
/*	
    {
        "review": "1 review article 1",
		"article":{
			"id": 1
		}
    }
*/	
	
    @PostMapping("/save")  
    public Review saveReview(@RequestBody Review review) {  
         return reviewService.addReview(review);           
    }  

	@PutMapping("/update") 
	public Review update(@RequestBody Review review) {
		return reviewService.update(review);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		reviewService.delete(reviewService.findById(id));
	}

}
