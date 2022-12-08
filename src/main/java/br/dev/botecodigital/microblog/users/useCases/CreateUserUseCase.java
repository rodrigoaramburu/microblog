package br.dev.botecodigital.microblog.users.useCases;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.dev.botecodigital.microblog.users.exceptions.ConflictUserException;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;


@Component
public class CreateUserUseCase {
	
	@Autowired
	private UserRepository userRepository;

	public User execute(User user) {
		
		Set<String> conflicts = new HashSet<>(); 
		Optional<User> optionalUserUsername = this.userRepository.findByUsername(user.getUsername());
		if(optionalUserUsername.isPresent()) {
			conflicts.add("Já existe um usuário com este o username: "+user.getUsername());
		}

		Optional<User> optionalUserEmail = this.userRepository.findByEmail(user.getEmail());
		if(optionalUserEmail.isPresent()) {
			conflicts.add("Já existe um usuário com este o e-mail: "+user.getEmail());
		}

		
		if(!conflicts.isEmpty()) {
			throw new ConflictUserException(conflicts);			
		}
		this.userRepository.save(user);
		return user;
	}

	
	
}
