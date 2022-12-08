package br.dev.botecodigital.microblog.post.dto;

import jakarta.validation.constraints.NotBlank;

public class CreatePostDTO {

	@NotBlank
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
