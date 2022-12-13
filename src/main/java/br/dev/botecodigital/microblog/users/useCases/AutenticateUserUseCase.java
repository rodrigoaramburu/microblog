package br.dev.botecodigital.microblog.users.useCases;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.dev.botecodigital.microblog.security.BCrypt;
import br.dev.botecodigital.microblog.security.JwtGenerator;
import br.dev.botecodigital.microblog.users.dto.UserAuthDTO;
import br.dev.botecodigital.microblog.users.dto.UserDTO;
import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;
import io.jsonwebtoken.lang.Maps;
import jakarta.validation.Valid;

@Component
public class AutenticateUserUseCase {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private JwtGenerator jwtGenerator;
	
	public Optional<Map<String,Object>> execute(@Valid UserAuthDTO userAuthDTO) {

		Optional<User> optionalUser = this.userRepository.findByUsername(userAuthDTO.getUsername());
		if(!optionalUser.isPresent()) {
			return Optional.empty();
		}
		User user = optionalUser.get();
		if( BCrypt.checkpw(userAuthDTO.getPassword(), user.getPassword())) {
			
			return Optional.of(
					Maps.of("token", (Object) this.jwtGenerator.generateToken(user) )
					.and("user", new UserDTO(user)).
					build()
			);
			
		}
		
		return Optional.empty();
	}

}
