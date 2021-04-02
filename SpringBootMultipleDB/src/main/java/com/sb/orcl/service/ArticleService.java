package com.sb.orcl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.orcl.model.Article;
import com.sb.orcl.repo.ArticleRepo;

@Service
public class ArticleService {
	
	@Autowired
	ArticleRepo articleRepo;
	
/*	
    {
        "title": "article x",
        "reviews": [
            {
                "review": "1 review article 101"
            }
        ]
    }
*/	
	
	public Article save(Article article) {
		return articleRepo.save(article);
	}
	
	public List<Article> findAll() {
		
		return articleRepo.findAll();
	}
	
	public Article findById(long id) {
		return articleRepo.findById(id).get();
	}
	
	public void delete(Article article) {
		articleRepo.delete(article);
	}
	
	public Article update(Article article) {
		return articleRepo.save(article);
	}

}
