package com.api.project.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.api.project.blog.exception.ResourceNotFoundException;
import com.api.project.blog.model.Post;
import com.api.project.blog.payload.PostDto;
import com.api.project.blog.payload.PostResponse;
import com.api.project.blog.repository.PostRepository;
import com.api.project.blog.service.PostServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService implements PostServiceImpl{
   
	private PostRepository postRepository;
	private ModelMapper mapper;
	
    public PostService(PostRepository postRepository, ModelMapper mapper) {
		this.postRepository = postRepository;
		this.mapper = mapper;
	}
	
	@Override
	public PostDto createPost(PostDto postDto) {
		Post post =  mapToEntity(postDto);
		Post newPost = postRepository.save(post);	
		PostDto postResponse = mapToDTO(newPost);
 		return postResponse;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				:Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
				
		Page<Post> posts = postRepository.findAll(pageable);
		List<Post> listPost = posts.getContent();
 		List<PostDto> content = listPost.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
 		
 		PostResponse postResponse = new PostResponse();
 		postResponse.setContent(content);
 		postResponse.setPageNo(posts.getNumber());
 		postResponse.setPageSuze(posts.getSize());
 		postResponse.setTotalElements(posts.getTotalElements());
 		postResponse.setTotalPages(posts.getTotalPages());
 		postResponse.setLast(posts.isLast());
 		
 		return postResponse;
 	}

	private PostDto mapToDTO(Post post) {
		PostDto postDto = mapper.map(post, PostDto.class);
		return postDto;
	}
	
  	private Post mapToEntity(PostDto postDto) {
		Post post = mapper.map(postDto, Post.class);
		return post;
	}

	@Override
	public PostDto getPosById(Long id) {
		Post postId = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", "id", id));
 		return mapToDTO(postId);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		Post update = postRepository.save(post);
		return mapToDTO(update);
	}

	@Override
	public void deletePostById(Long id) {
	   Post postId = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", "id", id));
       postRepository.delete(postId);
	}
}
