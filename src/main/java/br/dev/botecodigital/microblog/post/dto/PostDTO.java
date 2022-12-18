package br.dev.botecodigital.microblog.post.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.dev.botecodigital.microblog.post.model.Post;
import br.dev.botecodigital.microblog.users.dto.UserDTO;

public class PostDTO {

	private UUID id;
	private String text;
	private UserDTO user;
	
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'" )
	private LocalDateTime createAt;

	public PostDTO(Post post) {
		this.id = post.getId();
		this.text = post.getText();
		this.createAt = post.getCreateAt();
		this.user = new UserDTO(post.getUser());
	}
	
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}



	public UUID getId() {
		return id;
	}



	public void setId(UUID id) {
		this.id = id;
	}



	public LocalDateTime getCreateAt() {
		return createAt;
	}



	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	

}
