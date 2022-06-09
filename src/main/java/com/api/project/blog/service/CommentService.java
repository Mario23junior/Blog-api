package com.api.project.blog.service;

import java.util.List;

import com.api.project.blog.payload.CommentDTO;

public interface CommentService {
  
	CommentDTO createComment(Long postId, CommentDTO commentDTO);
	List<CommentDTO> getCommentsByPostId(Long postId);
	CommentDTO getCommentsById(Long postId, Long id); 
	CommentDTO updateComment(Long postId, Long commentId , CommentDTO commentRequest);
}
