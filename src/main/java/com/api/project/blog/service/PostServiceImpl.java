package com.api.project.blog.service;

import com.api.project.blog.payload.PostDto;

public interface PostServiceImpl {
  
	PostDto createPost(PostDto postDto);
}
