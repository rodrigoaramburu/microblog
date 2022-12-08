package br.dev.botecodigital.microblog.post.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.dev.botecodigital.microblog.post.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID>{

}
