package br.dev.botecodigital.microblog.post.exceptions;

public class PostNotBelongsToUser extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostNotBelongsToUser(String message){
		super(message);
	}
	
}
