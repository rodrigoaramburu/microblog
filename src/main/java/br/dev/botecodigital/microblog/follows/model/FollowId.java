package br.dev.botecodigital.microblog.follows.model;

import br.dev.botecodigital.microblog.users.model.User;

public class FollowId {

	private User user;
	private User following;
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

}
