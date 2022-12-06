package br.dev.botecodigital.microblog.users.dto;

import br.dev.botecodigital.microblog.security.BCrypt;
import br.dev.botecodigital.microblog.users.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateDTO {

	@NotBlank
	private String username;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 6)
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public User createUser() {
		return new User(
			this.username,
			this.email,
			BCrypt.hashpw(this.password, BCrypt.gensalt(12))
		);
	}
	
}
