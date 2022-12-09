package br.dev.botecodigital.microblog.post.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.post.useCases.DeletePostUseCase;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class DeletePostController {
	
	@Autowired
	private DeletePostUseCase deletePostUseCase;

	@DeleteMapping(value="/api/posts/{postId}/delete")
	public ResponseEntity<Object> deletePost(@PathVariable("postId") String postId, HttpServletRequest request ){
		UUID authUserId = UUID.fromString((String) request.getAttribute("authUserId") );
		
		this.deletePostUseCase.execute(authUserId, UUID.fromString(postId));
		
		Map<String, String> message = new HashMap<>();
		message.put("code", "200");
		message.put("message", "Post deletado com sucesso");
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

}
