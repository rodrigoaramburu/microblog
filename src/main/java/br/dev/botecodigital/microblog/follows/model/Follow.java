package br.dev.botecodigital.microblog.follows.model;

import java.time.LocalDateTime;
import java.util.UUID;

import br.dev.botecodigital.microblog.users.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(FollowId.class)
public class Follow {

	@Id
	@ManyToOne
	private User user;
	
	@Id
	@ManyToOne
	private User following;
	
	private LocalDateTime followedAt;
	
	public Follow() {
		
	}

	public Follow(User user, User following) {
		super();
		this.user = user;
		this.following = following;
		this.followedAt = LocalDateTime.now();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFollowing() {
		return following;
	}

	public void setFollowing(User following) {
		this.following = following;
	}

	public LocalDateTime getFollowedAt() {
		return followedAt;
	}

	public void setFollowedAt(LocalDateTime followedAt) {
		this.followedAt = followedAt;
	}
	


	
	
}
