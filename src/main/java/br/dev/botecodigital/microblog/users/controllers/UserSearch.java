package br.dev.botecodigital.microblog.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.users.dto.UserDTO;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.useCases.SearchUserUserCase;

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
public class UserSearch {
	
	@Autowired
	private  SearchUserUserCase searchUserUserCase;

	@GetMapping(value="/api/users/search")
	public ResponseEntity<Object> search(@RequestParam("search") String search){
		
		List<User> users = this.searchUserUserCase.execute(search);
		
		List<UserDTO> usersDTO = users.stream().map( (user) -> new UserDTO(user)).toList();
		return ResponseEntity.status(HttpStatus.OK).body(usersDTO);
	}
}
