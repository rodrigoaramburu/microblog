package br.dev.botecodigital.microblog.follows.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.follows.useCases.FollowUseCase;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FollowController {

	@Autowired
	private FollowUseCase followUseCase;

	@PostMapping(value="/api/users/{username}/follow")
	public ResponseEntity<Object> follow(@PathVariable("username") String username, HttpServletRequest request){
		
		UUID authUserId = UUID.fromString((String) request.getAttribute("authUserId"));
		
		this.followUseCase.execute(authUserId, username);
		
		Map<String, String> message = new HashMap<>();
		message.put("code", "201");
		message.put("message", "Usuário seguido com sucesso.");
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}
}
