package com.api.project.blog.service;

import java.util.List;

import com.api.project.blog.payload.PostDto;

public interface PostServiceImpl {
  
	PostDto createPost(PostDto postDto);
	List<PostDto> getAllPosts();
	PostDto getPosById(Long id);
}
