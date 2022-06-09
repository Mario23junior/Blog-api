package com.api.project.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.api.project.blog.exception.ResourceNotFoundException;
import com.api.project.blog.model.Comment;
import com.api.project.blog.model.Post;
import com.api.project.blog.payload.CommentDTO;
import com.api.project.blog.repository.CommentRepository;
import com.api.project.blog.repository.PostRepository;
import com.api.project.blog.service.CommentService;

import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
		this.commentRepository  = commentRepository;
		this.postRepository  = postRepository;
	}
	
	@Override
	public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
		Comment comment = mapToEntity(commentDTO);
		
		Post post = postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("post","id",postId));
		
		comment.setPost(post);
		
		Comment newcomment = commentRepository.save(comment);
		return mapToDTO(newcomment);	
	}
	
	@Override
	public List<CommentDTO> getCommentsByPostId(Long postId) {
 	   List<Comment> comments = commentRepository.findByPostId(postId);
 	   return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());	
	}
	
	private CommentDTO mapToDTO(Comment comment) {
		CommentDTO commentDto = new CommentDTO();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDTO commentDto) {
		Comment coment = new Comment();
		coment.setId(commentDto.getId());
		coment.setName(commentDto.getName());
		coment.setEmail(commentDto.getEmail());
		coment.setBody(commentDto.getBody());
		return coment;
	
	}
}
