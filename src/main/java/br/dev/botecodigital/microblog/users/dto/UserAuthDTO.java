package br.dev.botecodigital.microblog.users.dto;

import jakarta.validation.constraints.NotBlank;

public class UserAuthDTO {

	
	@NotBlank
	private String username;
	
	@NotBlank 
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	
	
	
}
