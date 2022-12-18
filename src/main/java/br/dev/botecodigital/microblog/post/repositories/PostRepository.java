package br.dev.botecodigital.microblog.post.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.dev.botecodigital.microblog.post.model.Post;
import br.dev.botecodigital.microblog.users.model.User;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID>{

	@Query("SELECT "
			+ "p "
			+ "FROM Post p "
			+ "WHERE "
			+ "		(p.user.id IN (SELECT f.following.id FROM Follow f WHERE f.user.id = :userId) OR (p.user.id = :userId)) AND"
			+ "		p.createAt < :before "
			+ "ORDER BY p.createAt DESC")
	List<Post> timeline(UUID userId, LocalDateTime before, PageRequest of);

	@Query("SELECT "
			+ "p "
			+ "FROM Post p "
			+ "WHERE "
			+ "		(p.user.id IN (SELECT f.following.id FROM Follow f WHERE f.user.id = :userId)  OR (p.user.id = :userId)) AND"
			+ "		p.createAt > :after "
			+ "ORDER BY p.createAt DESC")
	List<Post> timelineUpdate(UUID userId, LocalDateTime after);

	
	List<Post> findByUserOrderByCreateAtDesc(User user, PageRequest pageRequest);


}
