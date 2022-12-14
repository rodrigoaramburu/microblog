package br.dev.botecodigital.microblog.post.useCases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.post.model.Post;
import br.dev.botecodigital.microblog.post.repositories.PostRepository;
import br.dev.botecodigital.microblog.shared.exceptions.ResourceNotFoundException;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;

@Service
public class UserPostUseCase {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postReposiotry;

	public List<Post> execute(String username, int page) {
		Optional<User> optinalUser = this.userRepository.findByUsername(username);
		if(optinalUser.isEmpty()) {
			throw new ResourceNotFoundException("Usuário "+username+" não encontrado");
		}
		User user = optinalUser.get();
		
		List<Post> posts = this.postReposiotry.findByUser(user, PageRequest.of(page, 2));
		
		return posts;
	}

}
