package br.dev.botecodigital.microblog.users.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.dev.botecodigital.microblog.security.BCrypt;
import br.dev.botecodigital.microblog.security.JwtGenerator;
import br.dev.botecodigital.microblog.users.dto.UserAuthDTO;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;
import jakarta.validation.Valid;

@Component
public class AutenticateUserUseCase {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private JwtGenerator jwtGenerator;
	
	public Optional<String> execute(@Valid UserAuthDTO userAuthDTO) {

		Optional<User> optionalUser = this.userRepository.findByUsername(userAuthDTO.getUsername());
		if(!optionalUser.isPresent()) {
			return Optional.empty();
		}
		User user = optionalUser.get();
		if( BCrypt.checkpw(userAuthDTO.getPassword(), user.getPassword())) {
			
			return Optional.of( this.jwtGenerator.generateToken(user) );
			
		}
		
		return Optional.empty();
	}

}
