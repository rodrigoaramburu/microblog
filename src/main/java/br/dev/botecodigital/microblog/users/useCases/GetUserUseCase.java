package br.dev.botecodigital.microblog.users.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.shared.exceptions.ResourceNotFoundException;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;

@Service
public class GetUserUseCase {

	@Autowired
	private UserRepository userRepository;

	public User execute(String username) {
		
		Optional<User> optionalUser = this.userRepository.findByUsername(username);
		
		if(optionalUser.isEmpty()) {
			throw new ResourceNotFoundException("Usuário não encontrado");
		}
				
		return optionalUser.get();
	}

	
	
}
