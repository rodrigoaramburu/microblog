package br.dev.botecodigital.microblog.follows.useCases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.follows.repositories.FollowRepository;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;

@Service
public class UserFollowingUseCase {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FollowRepository followRepository;

	public List<User> execute(UUID authUserId) {

		User user = this.userRepository.findById(authUserId).get();
		
		List<User> users = this.followRepository.findByUser(user);
		
		return users;
	}

}
