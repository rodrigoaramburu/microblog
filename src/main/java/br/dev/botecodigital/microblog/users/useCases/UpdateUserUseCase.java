package br.dev.botecodigital.microblog.users.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.security.BCrypt;
import br.dev.botecodigital.microblog.shared.exceptions.ResourceNotFoundException;
import br.dev.botecodigital.microblog.users.dto.UserUpdateDTO;
import br.dev.botecodigital.microblog.users.exceptions.ConflictUserException;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;
import jakarta.validation.Valid;

@Service
public class UpdateUserUseCase {

	@Autowired
	private UserRepository userRepository;

	public void execute(UUID uuid, @Valid UserUpdateDTO userUpdateDTO) {
		Optional<User> optionalUser = this.userRepository.findById(uuid);
		
		if(!optionalUser.isPresent()) {
			throw new ResourceNotFoundException("Usuário não encontrado");
		}
		User user = optionalUser.get();
		
		if(!checkUsernameAvaliable(user, userUpdateDTO.getUsername())) {
			throw new ConflictUserException("Username já está em uso");
		}
		user.setUsername(userUpdateDTO.getUsername());
		
		if(!checkEmailAvaliable(user, userUpdateDTO.getEmail())) {
			throw new ConflictUserException("E-mail já está em uso");
		}
		user.setEmail(userUpdateDTO.getEmail());
		
		if(userUpdateDTO.getPassword() != null) {
			String passworBCrypt = BCrypt.hashpw(userUpdateDTO.getPassword(), BCrypt.gensalt(12));
			user.setPassword(passworBCrypt);
		}
		
		
		userRepository.save(user);
	}

	private boolean checkEmailAvaliable(User user, String email) {
		if(user.getEmail().equals(email)) {
			return true;
		}
		
		Optional<User> optionalUser = this.userRepository.findByEmail(email);
		if(optionalUser.isPresent()) {
			return false;
		}
		
		return true;
	}

	private boolean checkUsernameAvaliable(User user, String username) {
		if(user.getUsername().equals(username)) {
			return true;
		}
		
		Optional<User> optionalUser = this.userRepository.findByUsername(username);
		if(optionalUser.isPresent()) {
			return false;
		}
		
		return true;
	}

	
	
}
