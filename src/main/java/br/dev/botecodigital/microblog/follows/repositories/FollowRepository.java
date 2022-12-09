package br.dev.botecodigital.microblog.follows.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.dev.botecodigital.microblog.follows.model.Follow;
import br.dev.botecodigital.microblog.follows.model.FollowId;
import br.dev.botecodigital.microblog.users.model.User;

@Repository
public interface FollowRepository extends CrudRepository<Follow, FollowId>{

	boolean existsByUserAndFollowing(User authUser, User followUser);

}
