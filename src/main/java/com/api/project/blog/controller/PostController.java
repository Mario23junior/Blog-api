package com.api.project.blog.controller;

import java.util.List;

import com.api.project.blog.payload.PostDto;
import com.api.project.blog.service.impl.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(
				 postService.createPost(postDto)
				,HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PostDto> getAllPosts() {
		return postService.getAllPosts();
	}
}
