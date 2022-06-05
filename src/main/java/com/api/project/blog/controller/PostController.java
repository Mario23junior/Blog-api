package com.api.project.blog.controller;

import com.api.project.blog.payload.PostDto;
import com.api.project.blog.payload.PostResponse;
import com.api.project.blog.service.impl.PostService;
import com.api.project.blog.utils.AppConstantsPage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = AppConstantsPage.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
			                        @RequestParam(value = "pageSize", defaultValue = AppConstantsPage.DEFAULT_PAGE_SIZE, required = false)int pageSize,
			                        @RequestParam(value = "sort",defaultValue = AppConstantsPage.DEFAULT_SORT_BY,required = false)String sortBy,
			                        @RequestParam(value = "sortDir",defaultValue = AppConstantsPage.DEFAULT_SORT_DIRECTION ,required = false)String sortDir
			) {
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PostDto> getPosById(@PathVariable(name = "id" ) Long id){
		return ResponseEntity.ok(postService.getPosById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> udpate(@RequestBody PostDto postDto, @PathVariable(name = "id") Long id) {
		PostDto postResponse = postService.updatePost(postDto, id);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deletePost(@PathVariable Long id){
		postService.deletePostById(id);
		return new ResponseEntity<>("Post deletado com sucesso,",HttpStatus.OK);
	}
}







