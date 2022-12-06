package br.dev.botecodigital.microblog.users.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.dev.botecodigital.microblog.users.model.User;


public class UserDTO {

	private String username;
	private String email;
	private LocalDateTime createAt;
	private String photo;

	public UserDTO(User user) {
		this.username = user.getUsername();
		this.email= user.getEmail();
		this.createAt= user.getCreateAt();
		this.photo = user.getPhoto();
	}
	
	

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public String getPhoto() {
		return photo;
	}

	
}
