package br.dev.botecodigital.microblog.users.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.dev.botecodigital.microblog.users.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>{

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

}
