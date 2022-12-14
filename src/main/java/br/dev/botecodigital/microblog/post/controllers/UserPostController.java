package br.dev.botecodigital.microblog.post.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.post.dto.PostDTO;
import br.dev.botecodigital.microblog.post.model.Post;
import br.dev.botecodigital.microblog.post.useCases.UserPostUseCase;

@RestController
@CrossOrigin(origins = "*", maxAge=3600)
public class UserPostController {

	@Autowired
	private UserPostUseCase userPostUseCase;

	@GetMapping(value="/api/users/{username}/posts")
	public ResponseEntity<Object> userPosts(
			@PathVariable("username") String username, 
			@RequestParam(value="page", defaultValue = "0") int page){
		
		List<Post> posts = this.userPostUseCase.execute(username, page);
		
		List<PostDTO> postsDTO = posts.stream().map( (post) -> new PostDTO(post) ).toList();
		
		return ResponseEntity.status(HttpStatus.OK).body(postsDTO);
	}
}
