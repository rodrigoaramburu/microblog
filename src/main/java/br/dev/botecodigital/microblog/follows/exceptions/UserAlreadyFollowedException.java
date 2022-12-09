package br.dev.botecodigital.microblog.follows.exceptions;

public class UserAlreadyFollowedException extends RuntimeException {

	public UserAlreadyFollowedException(String message) {
		super(message);
	}

}
