package br.dev.botecodigital.microblog.users.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.users.dto.UserAuthDTO;
import br.dev.botecodigital.microblog.users.useCases.AutenticateUserUseCase;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthUserController {

	@Autowired
	private AutenticateUserUseCase autenticateUserUseCase;

	@PostMapping(value="/api/autenticate")
	public ResponseEntity<Object> autenticate(@RequestBody @Valid UserAuthDTO userAuthDTO){
		
		Optional<String> token = this.autenticateUserUseCase.execute(userAuthDTO);
		
		if(token.isEmpty()) {
			Map<String,String> error = new HashMap<>();
			error.put("code", "401");
			error.put("message", "Username ou password errados");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}
		
		
		Map<String,String> tokenResponse = new HashMap<>();
		tokenResponse.put("token", token.get());
		return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
	}
}
