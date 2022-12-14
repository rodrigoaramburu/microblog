package br.dev.botecodigital.microblog.follows.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.follows.useCases.UserFollowingUseCase;
import br.dev.botecodigital.microblog.users.dto.UserDTO;
import br.dev.botecodigital.microblog.users.model.User;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserFollowing {
	
	@Autowired
	private UserFollowingUseCase userFollowingUseCase;

	@GetMapping(value="/api/users/following")
	public ResponseEntity<List<UserDTO>> following(HttpServletRequest request){
		
		UUID authUserId = UUID.fromString( (String) request.getAttribute("authUserId"));
		List<User> users = this.userFollowingUseCase.execute(authUserId );
		
		List<UserDTO> usersDTO = users.stream().map((user) -> new UserDTO(user)).toList();
		
		return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
	}

}
