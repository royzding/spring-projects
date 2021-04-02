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

import com.sb.orcl.model.Article;
import com.sb.orcl.service.ArticleService;

@RestController  
@CrossOrigin(origins="http://localhost:4200")  
@RequestMapping(value="/article")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	

    @PostMapping("save")  
    public Article saveArticle(@RequestBody Article article) {  
         return articleService.save(article);  
          
    }  

	@GetMapping("/list") 
	public List<Article> list() 
	{
		return articleService.findAll();
	}
	
	@GetMapping("/{id}")
	public Article get(@PathVariable("id") int id) {
		return articleService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		articleService.delete(articleService.findById(id));
	}
	
	@PutMapping("/update") 
	public Article update(@RequestBody Article article) {
		return articleService.update(article);
	}
	

}
