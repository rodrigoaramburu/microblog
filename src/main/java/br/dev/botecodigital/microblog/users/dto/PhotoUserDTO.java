package br.dev.botecodigital.microblog.users.dto;

import jakarta.validation.constraints.NotBlank;

public class PhotoUserDTO {

	@NotBlank
	private String photo;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
