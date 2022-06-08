package com.api.project.blog.repository;

import com.api.project.blog.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository  extends JpaRepository<Comment, Long>{
  
	
}
