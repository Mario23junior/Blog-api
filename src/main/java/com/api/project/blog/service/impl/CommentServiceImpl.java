package com.api.project.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.api.project.blog.exception.BlogApiException;
import com.api.project.blog.exception.ResourceNotFoundException;
import com.api.project.blog.model.Comment;
import com.api.project.blog.model.Post;
import com.api.project.blog.payload.CommentDTO;
import com.api.project.blog.repository.CommentRepository;
import com.api.project.blog.repository.PostRepository;
import com.api.project.blog.service.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;
	
	public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository, ModelMapper mapper) {
		this.commentRepository  = commentRepository;
		this.postRepository  = postRepository;
		this.mapper = mapper;
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
	public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentRequest) {
		Post post = postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("post","id",postId));
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> 
			new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}
		
		comment.setName(commentRequest.getName());
		comment.setEmail(commentRequest.getEmail());
		comment.setBody(commentRequest.getBody());
		
		Comment updateComment = commentRepository.save(comment);
		return mapToDTO(updateComment);
 	}
	
	
	@Override
	public void deleteComment(Long postId, Long commentId) {
		Post post = postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("post","id",postId));
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> 
		    new ResourceNotFoundException("Comment", "ids", commentId));
		
		 if(!comment.getPost().getId().equals(post.getId())) {
			   throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment  does not belang to post");
		   }
		 commentRepository.delete(comment);
	}
	
	@Override
	public List<CommentDTO> getCommentsByPostId(Long postId) {
 	   List<Comment> comments = commentRepository.findByPostId(postId);
 	   return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());	
	}
	
	private CommentDTO mapToDTO(Comment comment) {
       CommentDTO commenDto = mapper.map(comment, CommentDTO.class);
		return commenDto;
	}
	
	private Comment mapToEntity(CommentDTO commentDto) {
		Comment coment = mapper.map(commentDto, Comment.class);
		return coment;
	
	}

	@Override
	public CommentDTO getCommentsById(Long postId, Long commentsId) {
	   Post post = postRepository.findById(postId).orElseThrow(
			   () -> new ResourceNotFoundException("Post", "id", commentsId));
	   
	   Comment comment = commentRepository.findById(commentsId).orElseThrow(()
			   -> new ResourceNotFoundException("Comments","Id", commentsId));
	   
	   if(!comment.getPost().getId().equals(post.getId())) {
		   throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment  does not belang to post");
	   }
	   
	return mapToDTO(comment);
   		
	}
}












