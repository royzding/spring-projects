package com.sb.orcl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.orcl.model.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long>{

}
