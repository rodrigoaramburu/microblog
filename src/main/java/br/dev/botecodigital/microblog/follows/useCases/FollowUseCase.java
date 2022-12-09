package br.dev.botecodigital.microblog.follows.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.follows.exceptions.UserAlreadyFollowedException;
import br.dev.botecodigital.microblog.follows.model.Follow;
import br.dev.botecodigital.microblog.follows.repositories.FollowRepository;
import br.dev.botecodigital.microblog.shared.exceptions.ResourceNotFoundException;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;

@Service
public class FollowUseCase {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FollowRepository followRepository;

	public void execute(UUID authUserId, String username) {

		User authUser = this.userRepository.findById(authUserId).get();
		
		Optional<User> optionalFollowUser = this.userRepository.findByUsername(username);
		if(optionalFollowUser.isEmpty()) {
			throw new ResourceNotFoundException("Usuário "+username+" não encontrado.");
		}
		User followUser = optionalFollowUser.get();
		
		if( checkAlreadyFollowed(authUser, followUser)) {
			throw new UserAlreadyFollowedException("Usuário já seguido.");
		}
		
		Follow follow = new Follow(authUser, followUser);
		
		this.followRepository.save(follow);
	}

	private boolean checkAlreadyFollowed(User authUser, User followUser) {
		return this.followRepository.existsByUserAndFollowing(authUser, followUser);
	}

}
