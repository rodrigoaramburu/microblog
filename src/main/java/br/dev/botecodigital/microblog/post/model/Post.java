package br.dev.botecodigital.microblog.post.model;

import java.time.LocalDateTime;
import java.util.UUID;

import br.dev.botecodigital.microblog.users.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable=false)
	private LocalDateTime createAt;
	
	@ManyToOne
	private User user;
	
	public Post() {}

	public Post(String text, User user) {
		this.text = text;
		this.user = user;
		this.createAt = LocalDateTime.now();
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	
	
}
