package br.dev.botecodigital.microblog.post.useCases;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.post.model.Post;
import br.dev.botecodigital.microblog.post.repositories.PostRepository;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;

@Service
public class TimelineUseCase {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	public List<Post> execute(UUID authUserId, LocalDateTime before) {

		User user = this.userRepository.findById(authUserId).get();

		List<Post> posts = this.postRepository.timeline(user.getId(), before , PageRequest.of(0, 10));

		return posts;

	}

}
