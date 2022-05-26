package com.api.project.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.api.project.blog.exception.ResourceNotFoundException;
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
		Post post =  mapToEntity(postDto);
		Post newPost = postRepository.save(post);	
		PostDto postResponse = mapToDTO(newPost);
 		return postResponse;
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> posts = postRepository.findAll();
 		List<PostDto> convertPosts = posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
 		return convertPosts;
 	}

	private PostDto mapToDTO(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		return postDto;
	}
	
  	private Post mapToEntity(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}

	@Override
	public PostDto getPosById(Long id) {
		Post postId = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", "id", id));
 		return mapToDTO(postId);
	}
}
