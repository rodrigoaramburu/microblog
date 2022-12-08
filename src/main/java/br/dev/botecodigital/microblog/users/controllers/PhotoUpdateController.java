package br.dev.botecodigital.microblog.users.controllers;


import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.dev.botecodigital.microblog.shared.FileStoreService;
import br.dev.botecodigital.microblog.users.dto.PhotoUserDTO;
import br.dev.botecodigital.microblog.users.dto.UserDTO;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.useCases.UpdateUserPhotoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class PhotoUpdateController {
	
	@Autowired
	private UpdateUserPhotoUseCase updateUserPhotoUseCase;

	@PutMapping(value="/api/users/photo")
	public ResponseEntity<Object> updateUserPhoto(@RequestBody @Valid PhotoUserDTO photoUserDTO, HttpServletRequest request){
		
		UUID authUserId = UUID.fromString((String) request.getAttribute("authUserId")); 
		
		User user = updateUserPhotoUseCase.execute( authUserId, photoUserDTO.getPhoto());
		
		return ResponseEntity.status(HttpStatus.OK).body( new UserDTO(user));
	}
}
