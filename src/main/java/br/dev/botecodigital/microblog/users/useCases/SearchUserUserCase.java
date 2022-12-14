package br.dev.botecodigital.microblog.users.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.dev.botecodigital.microblog.users.model.User;
import br.dev.botecodigital.microblog.users.repositories.UserRepository;

@Service
public class SearchUserUserCase {

	@Autowired
	private UserRepository userRepository;

	public List<User> execute(String search) {

		List<User> users = this.userRepository.findByUsernameContainsOrEmailContains(search, search);
		
		return users;
	}

	
}
