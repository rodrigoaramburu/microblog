package br.dev.botecodigital.microblog.post.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.post.dto.CreatePostDTO;
import br.dev.botecodigital.microblog.post.model.Post;
import br.dev.botecodigital.microblog.post.repositories.PostRepository;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class CreatePostUseCase {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private PostRepository postRepository;
	
	@Transactional
	public Post execute(UUID authUserId, @Valid CreatePostDTO createPostDTO) {
		
		User user = this.userRepository.findById(authUserId).get();
		
		Post post = new Post(createPostDTO.getText(), user);
		this.postRepository.save(post);
		return post;
	}

}
