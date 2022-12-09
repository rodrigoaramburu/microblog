package br.dev.botecodigital.microblog.post.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.post.exceptions.PostNotBelongsToUser;
import br.dev.botecodigital.microblog.post.model.Post;
import br.dev.botecodigital.microblog.post.repositories.PostRepository;
import br.dev.botecodigital.microblog.shared.exceptions.ResourceNotFoundException;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;

@Service
public class DeletePostUseCase {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void execute(UUID authUserId, UUID postId) {
		Optional<Post> optionalPost = this.postRepository.findById(postId);
		
		if(optionalPost.isEmpty()) {
			throw new ResourceNotFoundException("Post não encontrado");
		}
		Post post = optionalPost.get();
		User user = this.userRepository.findById(authUserId).get();
		
		if(!post.getUser().equals(user)) {
			throw new PostNotBelongsToUser("O post não pertence ao usuário");
		}
		
		this.postRepository.delete(post);
		
	}



}
