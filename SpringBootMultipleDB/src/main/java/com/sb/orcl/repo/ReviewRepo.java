package com.sb.orcl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.orcl.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{

}
