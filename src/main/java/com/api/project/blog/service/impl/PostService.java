package com.api.project.blog.service.impl;

import com.api.project.blog.model.Post;
import com.api.project.blog.payload.PostDto;
import com.api.project.blog.repository.PostRepository;
import com.api.project.blog.service.PostServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class PostService implements PostServiceImpl{
   
	private PostRepository postRepository;
	
    public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		Post newPost = postRepository.save(post);	
		
		PostDto postResponse = new PostDto();
		postResponse.setId(newPost.getId());
		postResponse.setTitle(newPost.getTitle());
		postResponse.setDescription(newPost.getDescription());
		postResponse.setContent(newPost.getContent());
 		return postResponse;
	}

}
