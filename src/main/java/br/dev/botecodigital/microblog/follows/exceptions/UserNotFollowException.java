package br.dev.botecodigital.microblog.follows.exceptions;

public class UserNotFollowException extends RuntimeException {

	public UserNotFollowException(String message) {
		super(message);
	}

}
