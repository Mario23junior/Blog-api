package com.api.project.blog.service;

import com.api.project.blog.payload.CommentDTO;

public interface CommentService {
  
	CommentDTO createComment(Long postId, CommentDTO commentDTO);
}
