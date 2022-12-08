package br.dev.botecodigital.microblog.post.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.post.dto.CreatePostDTO;
import br.dev.botecodigital.microblog.post.dto.PostDTO;
import br.dev.botecodigital.microblog.post.model.Post;
import br.dev.botecodigital.microblog.post.useCases.CreatePostUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreatePostController {

	@Autowired
	private CreatePostUseCase createPostUseCase;

	@PostMapping(value="/api/posts/create")
	public ResponseEntity<PostDTO> createPost(@RequestBody @Valid CreatePostDTO createPostDTO, HttpServletRequest request){
		System.out.println((String) request.getAttribute("authUserId"));
		UUID authUserId = UUID.fromString((String) request.getAttribute("authUserId")); 
		
		Post post = this.createPostUseCase.execute(authUserId,createPostDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new PostDTO(post));
	}
}
