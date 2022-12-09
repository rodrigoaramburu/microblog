package br.dev.botecodigital.microblog.follows.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.follows.useCases.UnfollowUserUseCase;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UnfollowController {

	@Autowired
	private UnfollowUserUseCase unfollowUserUseCase;

	@PostMapping(value="/api/users/{username}/unfollow")
	public ResponseEntity<Object> unfollow(@PathVariable("username") String username, HttpServletRequest request){
		UUID authUserId = UUID.fromString((String) request.getAttribute("authUserId"));
		
		this.unfollowUserUseCase.execute(authUserId, username);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(authUserId+" "+username);
	}
}
