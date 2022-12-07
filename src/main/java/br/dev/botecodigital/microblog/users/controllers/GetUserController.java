package br.dev.botecodigital.microblog.users.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.users.dto.UserDTO;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.useCases.GetUserUseCase;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.lang.Maps.MapBuilder;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class GetUserController {

	@Autowired
	private GetUserUseCase getUser;

	@GetMapping(value="/api/users/{username}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("username") String username){
		
		User user = this.getUser.execute(username);
		
		return ResponseEntity.status(HttpStatus.OK).body( new UserDTO(user) );
	}
}
