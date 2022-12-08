package br.dev.botecodigital.microblog.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.users.dto.UserCreateDTO;
import br.dev.botecodigital.microblog.users.dto.UserDTO;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.useCases.CreateUserUseCase;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CreateUserController {

	@Autowired
	private CreateUserUseCase createUserUseCase;
	
	@PostMapping(value = "/api/register")
	public ResponseEntity<UserDTO> create(@RequestBody @Valid UserCreateDTO userCreateDTO){
		
		User user = this.createUserUseCase.execute(userCreateDTO.createUser());
		
		return ResponseEntity.status(201).body( new UserDTO(user));
	}
	
}
