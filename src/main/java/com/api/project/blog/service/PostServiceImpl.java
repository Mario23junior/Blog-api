package com.api.project.blog.service;

import com.api.project.blog.payload.PostDto;
import com.api.project.blog.payload.PostResponse;

public interface PostServiceImpl {
  
	PostDto createPost(PostDto postDto);
	PostResponse getAllPosts(int pageNo, int pageSize);
	PostDto getPosById(Long id);
	PostDto updatePost(PostDto postDto, Long id);
	void deletePostById(Long id);
 }
