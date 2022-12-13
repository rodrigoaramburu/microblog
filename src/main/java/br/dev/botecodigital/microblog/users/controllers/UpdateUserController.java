package br.dev.botecodigital.microblog.users.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.users.dto.UserDTO;
import br.dev.botecodigital.microblog.users.dto.UserUpdateDTO;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.useCases.UpdateUserUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UpdateUserController {

	@Autowired
	private UpdateUserUseCase updateUserUseCase;

	@PutMapping(value = "/api/users/update")
	public ResponseEntity<UserDTO> updateUser(HttpServletRequest request,
			@RequestBody @Valid UserUpdateDTO userUpdateDTO) {

		UUID authUserId = UUID.fromString((String) request.getAttribute("authUserId"));
		User user = this.updateUserUseCase.execute(authUserId, userUpdateDTO);

		return ResponseEntity.status(HttpStatus.OK).body(new UserDTO(user));
	}
}
