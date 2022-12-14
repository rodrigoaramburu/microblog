package br.dev.botecodigital.microblog.follows.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.dev.botecodigital.microblog.follows.model.Follow;
import br.dev.botecodigital.microblog.follows.model.FollowId;
import br.dev.botecodigital.microblog.users.model.User;

@Repository
public interface FollowRepository extends CrudRepository<Follow, FollowId>{

	boolean existsByUserAndFollowing(User authUser, User followUser);

	Optional<Follow> findByUserAndFollowing(User authUser, User followUser);

	@Query("   SELECT "
			+ "   uf "
			+ "FROM Follow f "
			+ "JOIN User u ON f.user = u "
			+ "JOIN User uf ON f.following = uf "
			+ "WHERE u = ?1")
	List<User> findByUser(User user);

}
