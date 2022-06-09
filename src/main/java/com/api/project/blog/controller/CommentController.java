package com.api.project.blog.controller;

import java.util.List;

import com.api.project.blog.payload.CommentDTO;
import com.api.project.blog.service.CommentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	private CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
 	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") long id, 
			                                        @RequestBody CommentDTO commentDto) {
		return new ResponseEntity<CommentDTO>(commentService.createComment(id, commentDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDTO> CommentsByPostId(@PathVariable(value = "postId") Long postId) {
		return commentService.getCommentsByPostId(postId);
	}
	
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "id") Long commentId) {
		  
		CommentDTO commentDto = commentService.getCommentsById(postId, commentId);
		
		return new ResponseEntity<CommentDTO>(commentDto,HttpStatus.OK);
	}
}
