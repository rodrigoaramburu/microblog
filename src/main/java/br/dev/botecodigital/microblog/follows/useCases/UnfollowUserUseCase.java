package br.dev.botecodigital.microblog.follows.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.follows.exceptions.UserNotFollowException;
import br.dev.botecodigital.microblog.follows.model.Follow;
import br.dev.botecodigital.microblog.follows.repositories.FollowRepository;
import br.dev.botecodigital.microblog.shared.exceptions.ResourceNotFoundException;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;

@Service
public class UnfollowUserUseCase {
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
		
		Optional<Follow> optionalFollow = this.followRepository.findByUserAndFollowing(authUser, followUser); 
		
		if(optionalFollow.isEmpty()) {
			throw new UserNotFollowException("Usuário não seguido.");
		}
		
		this.followRepository.delete(optionalFollow.get());
		
	}

}
